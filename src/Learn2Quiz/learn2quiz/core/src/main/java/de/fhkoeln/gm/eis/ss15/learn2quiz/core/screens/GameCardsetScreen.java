package de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens;

import com.badlogic.gdx.Screen;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.CardsStage;

public class GameCardsetScreen implements Screen {

	private CardsStage myGameCardsetStage;
	private Learn2Quiz myGame;
	
	public GameCardsetScreen(Learn2Quiz myGame) {
		myGameCardsetStage = new CardsStage(myGame);
		myGame.myInputMultiplexer.addProcessor(myGameCardsetStage);
	}

	@Override
	public void render(float delta) {
		myGameCardsetStage.act(delta);
		myGameCardsetStage.draw();
		
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
		myGameCardsetStage.dispose();		
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
		myGameCardsetStage.dispose();
		
	}
}
