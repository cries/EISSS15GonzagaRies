package de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens;

import com.badlogic.gdx.Screen;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.CardsetsStage;

public class CardsetsScreen implements Screen {

	private CardsetsStage myCardsetsStage;
	private Learn2Quiz myGame;
	
	public CardsetsScreen(Learn2Quiz myGame) {
		myCardsetsStage = new CardsetsStage(myGame);
		myGame.myInputMultiplexer.addProcessor(myCardsetsStage);
	}
	
	@Override
	public void render(float delta) {
		myCardsetsStage.act(delta);
		myCardsetsStage.draw();
		
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
		myCardsetsStage.dispose();
		
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
		myCardsetsStage.dispose();
		
	}

}
