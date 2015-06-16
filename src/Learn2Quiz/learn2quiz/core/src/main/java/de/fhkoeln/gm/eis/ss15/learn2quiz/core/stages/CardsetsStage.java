package de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.xmpp.XMPPHandler;

public class CardsetsStage extends Stage {
	
	
	public ResourceManager myResourceMgr;
	public SceneLoader mySceneLoader;
	public XMPPHandler myXMPPHandler;
	private Learn2Quiz myGame;
	
	
	public CardsetsStage(Learn2Quiz game) {
		myGame = game;
		//myRESTHandler = myGame.RESTHandler;
		myXMPPHandler = myGame.myXMPPHandler;
		myResourceMgr = myGame.myResourceHandler;
		mySceneLoader = myGame.mySceneLoader;
		loadScene("CardsetsScreen");
		addActor(mySceneLoader.getRoot());
	
	
		SimpleButtonScript btnBack = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnBack"));
        btnBack.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	myGame.showMainMenu();
            }
        });
        
        SimpleButtonScript btnEdit = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnEdit"));
        btnBack.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	//myGame.showMainMenu();
            }
        });
        
        SimpleButtonScript btnDelete = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnDelete"));
        btnDelete.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	//myGame.showMainMenu();
            }
        });
	
	

}


	private void loadScene(String scene) {
		mySceneLoader.loadScene(scene);
		
	}
		
	}
