package de.fhkoeln.gm.eis.ss15.learn2quiz.core;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.uwsoft.editor.renderer.SceneLoader;

public class GameStage extends Stage {
	
	public GameStage(){
		SceneLoader sl = new SceneLoader();
		sl.loadScene("MainScene");
		this.addActor(sl.getRoot());
	}
}
