package de.fhkoeln.gm.eis.ss15.learn2quiz.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverInfo.Identity;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.Subscription;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;


public class XMPPHandler {
	private XMPPConnection xmppConn;
	private AccountManager accMngr;
	private PubSubManager pubSubMngr;
	private String hostname;
	private String username;
	private ItemEventListener<Item> listener;

	public XMPPHandler(){
	}
	
	public Boolean isConnected(){
		if (xmppConn != null && xmppConn.isConnected()){
			return true;
		} else {
			return false;			
		}	
	}
	
	public Boolean createAccount(String username, String password){
		try {
			accMngr.createAccount(username, password);
			return true;
		} catch (XMPPException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean connect(String hostname, int port){
		
		if (xmppConn != null && xmppConn.isConnected()){
			return true;
		}
		ConnectionConfiguration connConfig = new ConnectionConfiguration(hostname, port);
		xmppConn = new XMPPConnection(connConfig);
		
		accMngr = new AccountManager(xmppConn);
		
		try{
			xmppConn.connect();
			pubSubMngr = new PubSubManager(xmppConn, "pubsub.localhost" );
		} catch(XMPPException e){
			System.out.println(e);
			return false;
		}
		
		this.hostname = hostname;
		
		return true;
	}
	
	public void disconnect() {
		xmppConn.disconnect();
	}
	
	public boolean login(String user, String password){
		try{
			xmppConn.login(user, password);
		} catch(XMPPException e){
			return false;
		}
		
		this.username = user;
		return true;
	}
	
	public List<String> getAllNodes() {
		
		List<String> entries = new ArrayList<String>();
		
		try{
			DiscoverItems itms = pubSubMngr.discoverNodes(null);
			Iterator<DiscoverItems.Item> it = itms.getItems();
			for (; it.hasNext();){
				entries.add(it.next().getNode());
			}
		} catch(XMPPException e){
			e.printStackTrace();
		}
		return entries;
	}
	
	public Boolean deleteNode(String node){
		try{
			pubSubMngr.deleteNode(node);
		} catch(XMPPException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<String> getAllSubscriptions(){
		List<String> entries = new ArrayList<String>();
		try{
			List<Subscription> subsList = pubSubMngr.getSubscriptions();
			
			for (Subscription currSub: subsList){
				entries.add(currSub.getNode());
			}
		} catch (XMPPException e){
			e.printStackTrace();
		}
		return entries;
	}
	
	@SuppressWarnings("unchecked")
	public boolean publishItemPayload(String nodeId, String elemName, String payloadData) {
		LeafNode node = null;
		
		if (payloadData.length() == 0){
			System.err.println("No payload found!");
			return false;
		}
		try {
			node = (LeafNode) pubSubMngr.getNode(nodeId);
		} catch (XMPPException e) {
			 System.err.println("Unknown errorcode: " + e.getXMPPError().getCode());
             return false;
		}
		if (node != null) {
			try {
				node.send(new PayloadItem(null, new SimplePayload(elemName, null, payloadData)));
			} catch (XMPPException e) {
				e.printStackTrace();
				System.err.println("Item could not be published");
				return false;
			}
		}
		
		return true;
	}
	
	public String getNodeInformation(String node_id) {
        String info = "";
        ServiceDiscoveryManager discoManager = ServiceDiscoveryManager .getInstanceFor(xmppConn);
        DiscoverInfo discoInfo;
        try {
            discoInfo = discoManager.discoverInfo(
                    "pubsub." + xmppConn.getHost(), node_id);

            Iterator<Identity> it = discoInfo.getIdentities();

            while (it.hasNext()) {
                DiscoverInfo.Identity identity = (DiscoverInfo.Identity) it.next();
                info += "Name:\t" + identity.getName() + "\n" + "Type:\t"
                        + identity.getType() + "\n" + "Category:\t"
                        + identity.getCategory() + "\n";

                LeafNode node = (LeafNode) pubSubMngr.getNode(node_id);

                List<Subscription> subs = node.getSubscriptions();

                if (subs.size() > 0) {
                    info += "Subscriptions:\n";

                    for (Subscription curr : subs) {
                        info += " " + curr.toXML() + "\n";
                    }
                    info += "\n";
                }
            }
        } catch (XMPPException e) {
            e.printStackTrace();
        }

        return info;
    }

	public boolean subscribeToNode(String nodeId){
		LeafNode node = null;
		
		try {
			node = (LeafNode) pubSubMngr.getNode(nodeId);
			node.subscribe(this.username + "@" + this.hostname);
			node.addItemEventListener(listener);
			
		} catch (XMPPException e) {
			 System.err.println("Unknown errorcode: " + e.getXMPPError().getCode());
             return false;
		}
		return true;
	}
	
	public boolean unsubscribeToNode(String nodeId){
		LeafNode node = null;
        try {
            node = (LeafNode) pubSubMngr.getNode(nodeId);
            node.unsubscribe(this.username + "@" + this.hostname);
            node.removeItemEventListener(listener);
        } catch (XMPPException e) {
            System.err.println("Unsubscribing failed!");
            e.printStackTrace();
            return false;
        }
        
        return true;
	}
	
	public void addItemListener(ItemEventListener<Item> listener) {
        this.listener = listener;
        
        attachListenerToSubNodes();
    }
	
	private void attachListenerToSubNodes() {
		List<Subscription> subscriptions;
		try {
			subscriptions = pubSubMngr.getSubscriptions();
		} catch (XMPPException e) {
			System.err.println("Couldn't get subscriptions!");
            e.printStackTrace();
            return;
		}
		
		for (Subscription currSub : subscriptions) {
			try {
				pubSubMngr.getNode(currSub.getNode()).addItemEventListener(listener);
			} catch(XMPPException e1) {
				System.err.println("Couldn't get node to attach listener!");
			}
		}
	}
	
	public boolean createNode(String nodeid) {
		try {
			LeafNode leaf = pubSubMngr.createNode(nodeid);
	     	leaf.sendConfigurationForm(createForm(FormType.submit, true, true, PublishModel.open, AccessModel.open));
		} catch (XMPPException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private ConfigureForm createForm(FormType type, boolean pers, boolean payload, PublishModel pm, AccessModel am) {
		ConfigureForm form = new ConfigureForm(type);
        form.setPersistentItems(pers);
        form.setDeliverPayloads(payload);
        form.setPublishModel(pm);
        form.setAccessModel(am);

        return form;
	}
}
