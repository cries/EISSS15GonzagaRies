package de.fhkoeln.gm.eis.ss15.learn2quiz.client.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;







import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.client.xmpp.ItemLoggingHandler;
import de.fhkoeln.gm.eis.ss15.learn2quiz.client.xmpp.XMPPHandler;

public class Learn2QuizDesktop {
	private static DesktopApp myDesktopApp;
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 768;
		myDesktopApp = new DesktopApp(config);
	}
	
}
