package de.fhkoeln.gm.eis.ss15.learn2quiz.html;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class Learn2QuizHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new Learn2Quiz();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
