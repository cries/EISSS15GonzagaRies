package de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens;

import com.badlogic.gdx.Screen;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.GameStage;

public class GameScreen implements Screen {

	private GameStage myGameStage;
	private Learn2Quiz myGame;
	
	public GameScreen(Learn2Quiz myGame) {
		myGameStage = new GameStage(myGame);
		myGame.myInputMultiplexer.addProcessor(myGameStage);
	}

	@Override
	public void render(float delta) {
		myGameStage.act(delta);
		myGameStage.draw();
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
		myGameStage.dispose();
		
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
		myGameStage.dispose();
		
	}
	
}
