package de.fhkoeln.gm.eis.ss15.learn2quiz.android;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class Learn2QuizActivity extends AndroidApplication {

	@Override
	public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
			initialize(new Learn2Quiz(), config);
	}
}
