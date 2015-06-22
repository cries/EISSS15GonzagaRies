package de.fhkoeln.gm.eis.ss15.learn2quiz.core.xmpp;

import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;

public class ItemLoggingHandler implements ItemEventListener<Item>{
	
	Learn2Quiz myDesktopApp;
	
	public ItemLoggingHandler(Learn2Quiz myDesktopApp){
		this.myDesktopApp = myDesktopApp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handlePublishedItems(ItemPublishEvent<Item> event) {
		for (Item currItem: event.getItems()) {
			myDesktopApp.receiveNotification(((PayloadItem<SimplePayload>) currItem).getPayload().toXML());
		}
	}

}
