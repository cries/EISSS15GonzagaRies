package de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens;

import com.badlogic.gdx.Screen;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.GameGroupStage;

public class GameGroupScreen implements Screen {

	private GameGroupStage myGameGroupStage;
	private Learn2Quiz myGame;
	
	public GameGroupScreen(Learn2Quiz myGame) {
		myGameGroupStage = new GameGroupStage(myGame);
		myGame.myInputMultiplexer.addProcessor(myGameGroupStage);
	}

	@Override
	public void render(float delta) {
		myGameGroupStage.act(delta);
		myGameGroupStage.draw();
		
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
		myGameGroupStage.dispose();		
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
		myGameGroupStage.dispose();
		
	}
	
}
