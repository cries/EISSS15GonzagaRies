package de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.xmpp.XMPPHandler;

public class MainMenuStage extends Stage {
	
	public ResourceManager myResourceMgr;
	public SceneLoader mySceneLoader;
	public XMPPHandler myXMPPHandler;
	private Learn2Quiz myGame;
	private Boolean connected = false;
	private TextField txtUsername;
	private TextField txtPassword;

	public MainMenuStage(Learn2Quiz game) {
		myGame = game;
		myXMPPHandler = myGame.myXMPPHandler;
		myResourceMgr = myGame.myResourceHandler;
		mySceneLoader = myGame.mySceneLoader;
		loadScene("MainMenuScreen");
		addActor(mySceneLoader.getRoot());
		
		SimpleButtonScript btnGruppen = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnGruppen"));
		btnGruppen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	myGame.showGroups();
            }
        });
        
        SimpleButtonScript btnSpielen = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnSpielen"));
        btnSpielen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	//myGame.startGame();
            }
        });
        
        SimpleButtonScript btnEinladungen = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnEinladungen"));
        btnEinladungen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	//myGame.showEinladungen();
            }
        });
        
        SimpleButtonScript btnHome = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnBreadcrumbHome"));
        btnHome.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	myGame.showMainMenu();
            }
        });
        
//        SimpleButtonScript btnGroup = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnBreadcrumb2"));
//        btnGroup.addListener(new ClickListener() {
//            public void clicked (InputEvent event, float x, float y) {
//            	//myGame.showEinladungen();
//            }
//        });
        
        SimpleButtonScript btnSchliessen = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnSchliessen"));
        btnSchliessen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	Gdx.app.exit();
            }
        });
	}

	private void loadScene(String scene) {
		mySceneLoader.loadScene(scene);
	}

}
