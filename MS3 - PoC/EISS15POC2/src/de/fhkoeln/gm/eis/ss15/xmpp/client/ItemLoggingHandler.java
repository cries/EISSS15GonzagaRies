package de.fhkoeln.gm.eis.ss15.xmpp.client;

import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import de.fhkoeln.gm.eis.ss15.xmpp.client.XMPPClient;

public class ItemLoggingHandler implements ItemEventListener<Item>{
	
	private XMPPClient clFrame;
	
	public ItemLoggingHandler(XMPPClient clFrame){
		this.clFrame = clFrame;
	}

	@Override
	public void handlePublishedItems(ItemPublishEvent<Item> event) {
		for (Item currItem: event.getItems()) {
			clFrame.receiveNotification(((PayloadItem<SimplePayload>) currItem).getPayload().toXML());
		}
	}

}
