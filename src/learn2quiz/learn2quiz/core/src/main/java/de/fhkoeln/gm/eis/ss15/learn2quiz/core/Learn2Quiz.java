package de.fhkoeln.gm.eis.ss15.learn2quiz.core;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class Learn2Quiz implements ApplicationListener {
	Texture texture;
	SpriteBatch batch;
	float elapsed;
	private Boolean gameStarted;
	
	private GameStage stage;

	@Override
	public void create () {
		stage = new GameStage(this);
		gameStarted = true;
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (gameStarted) {
			stage.act();
			stage.draw();
		}
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
		Gdx.app.exit();
	}

	public void startGame() {
		// TODO Auto-generated method stub
		gameStarted = true;
	}
}
