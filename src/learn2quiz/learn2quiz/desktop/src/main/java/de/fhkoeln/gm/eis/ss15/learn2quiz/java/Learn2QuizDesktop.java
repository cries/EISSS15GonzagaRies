package de.fhkoeln.gm.eis.ss15.learn2quiz.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;

public class Learn2QuizDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 768;
		new LwjglApplication(new Learn2Quiz(), config);
	}
}
