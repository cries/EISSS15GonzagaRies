package de.fhkoeln.gm.eis.ss15.learn2quiz.client.xmpp;

import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import de.fhkoeln.gm.eis.ss15.learn2quiz.client.desktop.DesktopApp;


public class ItemLoggingHandler implements ItemEventListener<Item>{
	
	DesktopApp myDesktopApp;
	
	public ItemLoggingHandler(DesktopApp myDesktopApp){
		this.myDesktopApp = myDesktopApp;
	}

	@Override
	public void handlePublishedItems(ItemPublishEvent<Item> event) {
		for (Item currItem: event.getItems()) {
			myDesktopApp.receiveNotification(((PayloadItem<SimplePayload>) currItem).getPayload().toXML());
		}
	}

}
