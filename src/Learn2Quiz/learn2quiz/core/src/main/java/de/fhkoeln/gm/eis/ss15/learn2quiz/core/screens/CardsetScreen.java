package de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens;

import com.badlogic.gdx.Screen;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.CardsetStage;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.InvitesStage;

public class CardsetScreen implements Screen{


	private CardsetStage myCardsetStage;
	private Learn2Quiz myGame;
	
	public CardsetScreen(Learn2Quiz myGame) {
		myCardsetStage = new CardsetStage(myGame);
		myGame.myInputMultiplexer.addProcessor(myCardsetStage);
	
}

	@Override
	public void render(float delta) {
		myCardsetStage.act(delta);
		myCardsetStage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		myCardsetStage.dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		myCardsetStage.dispose();
		
	}
}