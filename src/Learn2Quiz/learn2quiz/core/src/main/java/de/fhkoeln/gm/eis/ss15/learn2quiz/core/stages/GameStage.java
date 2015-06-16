package de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.xmpp.XMPPHandler;

public class GameStage extends Stage {
	
	public ResourceManager myResourceMgr;
	public SceneLoader mySceneLoader;
	public XMPPHandler myXMPPHandler;
	private Learn2Quiz myGame;
	
	public GameStage(Learn2Quiz game) {
		myGame = game;
		//myRESTHandler = myGame.RESTHandler;
		myXMPPHandler = myGame.myXMPPHandler;
		myResourceMgr = myGame.myResourceHandler;
		mySceneLoader = myGame.mySceneLoader;
		loadScene("GroupsScreen");
		addActor(mySceneLoader.getRoot());
	
		  SimpleButtonScript chkAnswer1 = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("chkAnswer1"));
		  chkAnswer1.addListener(new ClickListener() {
	            public void clicked (InputEvent event, float x, float y) {
	            	//myGame.showMainMenu();
	            }
	        });
	

		  SimpleButtonScript chkAnswer2 = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("chkAnswer2"));
		  chkAnswer2.addListener(new ClickListener() {
	            public void clicked (InputEvent event, float x, float y) {
	            	//myGame.showMainMenu();
	            }
	        });
		  

		  SimpleButtonScript chkAnswer3 = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("chkAnswer3"));
		  chkAnswer3.addListener(new ClickListener() {
	            public void clicked (InputEvent event, float x, float y) {
	            	//myGame.showMainMenu();
	            }
	        });
		  
		  SimpleButtonScript btnJoker = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnJoker"));
		  btnJoker.addListener(new ClickListener() {
	            public void clicked (InputEvent event, float x, float y) {
	            	//myGame.showMainMenu();
	            }
	        });
		  
		  SimpleButtonScript btnNext = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnNext"));
		  btnNext.addListener(new ClickListener() {
	            public void clicked (InputEvent event, float x, float y) {
	            	//myGame.showMainMenu();
	            }
	        });
}
	
	private void loadScene(String scene) {
		mySceneLoader.loadScene(scene);
		
	}
		
	}
