package de.fhkoeln.gm.eis.ss15.xmpp.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import de.fhkoeln.gm.eis.ss15.xmpp.connection.XMPPConnectionHandler;



public class XMPPClient extends JFrame {
	
	    private static JPanel contentPane;
	    private static JTabbedPane tabbedPane;
	    private static XMPPConnectionHandler connHndlr;
	    private static String username;
	    private static JTextPane xmppOutput;
	    
	 
	     public XMPPClient(String host, int port, String user, String pass)
	    {
	    	this.username = user;
	    	connHndlr = new XMPPConnectionHandler();
	    	
	    	 final XMPPConnectionHandler connHndlr = new XMPPConnectionHandler();
				if (connHndlr.connect(host, port)){
					if (connHndlr.login(user, pass)){
						System.out.println("Connect & Anmeldung erfolgreich!");
						// Open Second Frame
					} else {
						System.out.println("Anmeldung fehlgeschlagen!");
					}
				} else {
					System.out.println("Connect fehlgeschlagen!");
				}
				
	        JFrame frame = new JFrame("XMPP Client - " + user);
	        
	        connHndlr.addItemListener(new ItemLoggingHandler(this));
	        
	        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        frame.setBounds(400, 400, 400, 300);
	        
	        JPanel panel4 = new JPanel();
	        panel4.setPreferredSize( new Dimension( 150, 50 ) );
	        xmppOutput = new JTextPane();
	        xmppOutput.setPreferredSize( new Dimension( 150, 50 ) );
	        panel4.add(xmppOutput);
	        
	        
	        JButton SubscribeTopic = new JButton("Subscribe Topic1");  // create the button
	        SubscribeTopic.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					connHndlr.subscribeToNode("Topic1");
					List<String> entries = connHndlr.getAllNodes();
					
					for (String curr: entries) {
						System.out.println("Node: " + curr);
					}
					
					List<String> entries1 = connHndlr.getAllSubscriptions();
					
					for (String curr: entries1) {
						System.out.println("sub: " + curr);
					}
					
					if (!connHndlr.subscribeToNode("Topic1")){
						connHndlr.unsubscribeToNode("Topic1");
					}
				}
			});
	        JPanel panel1 = new JPanel();  // create the panel
	        panel1.add(SubscribeTopic);  // add the button to the panel
	        

	        JButton CreateTopic = new JButton("Create Topic1");
	        CreateTopic.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					connHndlr.deleteNode("Topic1");
					connHndlr.createNode("Topic1");
				}
			});
	        
	        JPanel panel2 = new JPanel();
	        panel2.add(CreateTopic);
	        
	        
	        JButton CreateTopic2 = new JButton("Create Topic2");
	        CreateTopic2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					connHndlr.deleteNode("Topic2");
					connHndlr.createNode("Topic2");
				}
			});
	        
	        panel2.add(CreateTopic2);
	        
	        JButton SubscribeTopic2 = new JButton("Subscribe Topic2"); 
	        SubscribeTopic2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					connHndlr.subscribeToNode("Topic2");
				}
			});
	        panel1.add(SubscribeTopic2);  // add the button to the panel
	        
	        JPanel panel3 = new JPanel();
	        
	        JButton SendMessageTopic1 = new JButton("Send Broadcast Topic1");
	        SendMessageTopic1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					connHndlr.publishItemPayload("Topic1", "TestElement", "TestData");
					
				}
			});
	        
	        panel3.add(SendMessageTopic1);
	        frame.add(panel1, BorderLayout.NORTH);  // add the panel to the frame
	        frame.add(panel2, BorderLayout.SOUTH);
	        frame.add(panel3, BorderLayout.EAST);
	        frame.add(panel4, BorderLayout.WEST);
	        frame.setVisible(true);
	       
	    }

		public void receiveNotification(String xml) {
//			xmppOutput.setText(xmppOutput.getText() + "<"
//					+ "..." + ">" + xml + "\n");
			System.out.println("User :" + this.username + " Data: " + xml);
		}
}
