package de.fhkoeln.gm.eis.ss15.learn2quiz.core;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.resources.ResourceManager;

public class GameStage extends Overlap2DStage {
	
	private Learn2Quiz demo; 
	
	private ResourceManager assetManager;
	
	public GameStage(Learn2Quiz demo) {
		super();
		
		Gdx.input.setInputProcessor(this);
		this.demo = demo;
		
		initSceneLoader();
		initMenu();
	}
	
	private void initMenu(){
		clear();
		sceneLoader.loadScene("MainScene");
		
		MenuScreenScript menuScript = new MenuScreenScript(this);
		sceneLoader.getRoot().addScript(menuScript);
		addActor(sceneLoader.getRoot());
	}
	
	public void startGame(){
		clear();
		demo.startGame();
	}
	
	public void close() {
		demo.dispose();
	}
}
