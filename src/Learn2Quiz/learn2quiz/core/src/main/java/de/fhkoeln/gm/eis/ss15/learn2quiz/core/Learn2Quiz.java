package de.fhkoeln.gm.eis.ss15.learn2quiz.core;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.uwsoft.editor.renderer.resources.ResourceManager;

public class Learn2Quiz implements ApplicationListener {
	
	private GameStage myGameStage;
	private MenuStage myMenuStage;
	private ResourceManager rm;
	private InputMultiplexer inputMultiplexer;
	
	@Override
	public void create () {
		inputMultiplexer = new InputMultiplexer();
		ResourceManager rm = new ResourceManager();
		rm.initAllResources();
		
		myGameStage = new GameStage(rm);
		myMenuStage = new MenuStage(myGameStage);
		
		inputMultiplexer.addProcessor(myGameStage);
		inputMultiplexer.addProcessor(myMenuStage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		myGameStage.act();
		myGameStage.draw();
		
		myMenuStage.act();
		myMenuStage.draw();
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
