package de.fhkoeln.gm.eis.ss15.learn2quiz.client.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;


public class DesktopApp {
	public DesktopApp(LwjglApplicationConfiguration myConfig) {
		new LwjglApplication(new Learn2Quiz(), myConfig);
	}
}
