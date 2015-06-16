package de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens;

import com.badlogic.gdx.Screen;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.CardsStage;

public class CardssetScreen implements Screen {

	private CardsStage myCardssetStage;
	private Learn2Quiz myGame;
	
	public CardssetScreen(Learn2Quiz myGame) {
		myCardssetStage = new CardsStage(myGame);
		myGame.myInputMultiplexer.addProcessor(myCardssetStage);
	}
	
	@Override
	public void render(float delta) {
		myCardssetStage.act(delta);
		myCardssetStage.draw();
		
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
		myCardssetStage.dispose();
		
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
		myCardssetStage.dispose();
		
	}

}
