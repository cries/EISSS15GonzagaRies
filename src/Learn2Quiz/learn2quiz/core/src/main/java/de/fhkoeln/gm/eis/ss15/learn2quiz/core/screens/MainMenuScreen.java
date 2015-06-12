package de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens;

import com.badlogic.gdx.Screen;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.LoginStage;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.MainMenuStage;

public class MainMenuScreen implements Screen {
	
	private MainMenuStage myMainMenuStage;
	
	public MainMenuScreen(Learn2Quiz myGame) {
		myMainMenuStage = new MainMenuStage(myGame);
		myGame.myInputMultiplexer.addProcessor(myMainMenuStage);
	}

	@Override
	public void render(float delta) {
		myMainMenuStage.act(delta);
		myMainMenuStage.draw();
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
		myMainMenuStage.dispose();
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
		myMainMenuStage.dispose();
	}

}
