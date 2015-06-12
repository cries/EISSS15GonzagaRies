package de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.uwsoft.editor.renderer.resources.ResourceManager;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.LoginStage;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.WelcomeStage;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.xmpp.XMPPHandler;

public class WelcomeScreen implements Screen {
	
	private WelcomeStage myWelcomeStage;
	
	public WelcomeScreen(Learn2Quiz myGame) {

		myWelcomeStage = new WelcomeStage(myGame);
		
		myGame.myInputMultiplexer.addProcessor(myWelcomeStage);
		Gdx.input.setInputProcessor(myGame.myInputMultiplexer);
	}

	@Override
	public void render(float delta) {
		//Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		myWelcomeStage.act(delta);
		myWelcomeStage.draw();
		
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
		myWelcomeStage.dispose();
		
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
		myWelcomeStage.dispose();
		
	}

}
