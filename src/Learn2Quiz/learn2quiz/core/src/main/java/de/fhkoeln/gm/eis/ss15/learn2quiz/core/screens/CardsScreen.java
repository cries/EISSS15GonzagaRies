package de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens;

import com.badlogic.gdx.Screen;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.CardsStage;

public class CardsScreen implements Screen {
	
	private CardsStage myCardsStage;
	private Learn2Quiz myGame;
	
	public CardsScreen(Learn2Quiz myGame) {
		myCardsStage = new CardsStage(myGame);
		myGame.myInputMultiplexer.addProcessor(myCardsStage);
	}

	@Override
	public void render(float delta) {
		myCardsStage.act(delta);
		myCardsStage.draw();
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
		myCardsStage.dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		myCardsStage.dispose();
	}

}
