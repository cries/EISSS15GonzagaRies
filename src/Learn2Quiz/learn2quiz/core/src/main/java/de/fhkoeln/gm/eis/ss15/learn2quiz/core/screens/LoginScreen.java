package de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.LoginStage;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.WelcomeStage;

public class LoginScreen implements Screen {
	
	private LoginStage myLoginStage;
	
	public LoginScreen(Learn2Quiz myGame) {
		myLoginStage = new LoginStage(myGame);
		myGame.myInputMultiplexer.addProcessor(myLoginStage);
		//Gdx.input.setInputProcessor(myGame.myInputMultiplexer);
	}

	@Override
	public void render(float delta) {
		myLoginStage.act(delta);
		myLoginStage.draw();
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
		myLoginStage.dispose();
		
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
		myLoginStage.dispose();
		
	}

}
