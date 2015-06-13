package de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.xmpp.ItemLoggingHandler;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.xmpp.XMPPHandler;

public class WelcomeStage extends Stage {
	
	public ResourceManager myResourceMgr;
	public SceneLoader mySceneLoader;
	public XMPPHandler myXMPPHandler;
	public Learn2Quiz myGame;
	
	public WelcomeStage(Learn2Quiz game){
		myGame = game;
		myXMPPHandler = myGame.myXMPPHandler;
		myResourceMgr = myGame.myResourceHandler;
		mySceneLoader = myGame.mySceneLoader;
		loadScene("WelcomeScene");
		addActor(mySceneLoader.getRoot());
		
		// Creating restart button, and adding a click listener to it
        SimpleButtonScript btnEinloggen = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnEinloggen"));
        btnEinloggen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	System.out.println("Click: Einloggen");
            	myGame.showLogin();
            }
        });
        
        SimpleButtonScript btnRegistrieren = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnRegistrieren"));
        btnRegistrieren.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                //restarting game when clicked
                //gameStage.restart();
            	System.out.println("Click: Registrieren");
            	myGame.showRegister();
            }
        });
        
	}
	
	@Override
    public void draw() {
        super.draw();
    }
	
	public void loadScene(String scene) {
		mySceneLoader.loadScene(scene);
	}
	
	
}
