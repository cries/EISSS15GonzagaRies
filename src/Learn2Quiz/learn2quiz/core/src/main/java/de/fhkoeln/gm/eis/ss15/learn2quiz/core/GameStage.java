package de.fhkoeln.gm.eis.ss15.learn2quiz.core;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;

public class GameStage extends Overlap2DStage {
	
	public ResourceManager rm;
	private SceneLoader sl;
	
	public GameStage(ResourceManager rm){
		this.rm = rm;
		this.sl = new SceneLoader(this.rm);
		sl.loadScene("MainScene");
		this.addActor(sl.getRoot());
	}
}
