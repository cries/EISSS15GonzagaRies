package de.fhkoeln.gm.eis.ss15.learn2quiz.client.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.fhkoeln.gm.eis.ss15.learn2quiz.client.xmpp.ItemLoggingHandler;
import de.fhkoeln.gm.eis.ss15.learn2quiz.client.xmpp.XMPPHandler;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;

public class DesktopApp {
	
	private final XMPPHandler myXMPPHandler;
	//private RESTHandler myRESTHandler;
	
	public DesktopApp(LwjglApplicationConfiguration myConfig) {
		new LwjglApplication(new Learn2Quiz(), myConfig);
		myXMPPHandler = new XMPPHandler();
		
		if (myXMPPHandler.connect("localhost", 5222)){
			if (myXMPPHandler.login("user1", "login")){
				System.out.println("Connect & Anmeldung erfolgreich!");
				// Open Second Frame
				myXMPPHandler.addItemListener(new ItemLoggingHandler(this));
				myXMPPHandler.publishItemPayload("node1", "elem2", "data1");
				//System.out.println(myXMPPHandler.getNodeInformation("node1"));
			} else {
				System.out.println("Anmeldung fehlgeschlagen!");
			}
		} else {
			System.out.println("Connect fehlgeschlagen!");
		}
	}

	public void receiveNotification(String xml) {
		System.out.println("XMPP Output: " + xml);
	}
}
