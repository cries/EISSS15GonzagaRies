package de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens;

import com.badlogic.gdx.Screen;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.CardsStage;

public class DiscussionScreen implements Screen {

	private CardsStage myDiscussionStage;
	private Learn2Quiz myGame;
	
	public DiscussionScreen(Learn2Quiz myGame) {
		myDiscussionStage = new CardsStage(myGame);
		myGame.myInputMultiplexer.addProcessor(myDiscussionStage);
	
	
	
}

	@Override
	public void render(float delta) {
		myDiscussionStage.act(delta);
		myDiscussionStage.draw();
		
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
		myDiscussionStage.dispose();
		
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
		myDiscussionStage.dispose();
		
	}
}