package de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens;

import com.badlogic.gdx.Screen;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.LoginStage;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.RegisterStage;

public class RegisterScreen implements Screen{
	
	private RegisterStage myRegisterStage;
	
	public RegisterScreen(Learn2Quiz myGame) {
		myRegisterStage = new RegisterStage(myGame);
		myGame.myInputMultiplexer.addProcessor(myRegisterStage);
	}

	@Override
	public void render(float delta) {
		myRegisterStage.act(delta);
		myRegisterStage.draw();
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
		myRegisterStage.dispose();
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
		myRegisterStage.dispose();
	}

}
