package de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.xmpp.XMPPHandler;

public class LoginStage extends Stage {
	
	public ResourceManager myResourceMgr;
	public SceneLoader mySceneLoader;
	public XMPPHandler myXMPPHandler;
	public Learn2Quiz myGame;
	private Boolean connected = false;
	Skin skin;
	
    public LoginStage(Learn2Quiz game) {
    	
    	skin = new Skin( Gdx.files.internal( "uiskin.json" ));
    	myGame = game;
		myXMPPHandler = game.myXMPPHandler;
		myResourceMgr = game.myResourceHandler;
		mySceneLoader = game.mySceneLoader;
		loadScene("LoginScene");
		addActor(mySceneLoader.getRoot());
		
		SimpleButtonScript btnLogin = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnLogin"));
        btnLogin.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	if (!connected && myXMPPHandler.connect("localhost", 5222)){
        			if (myXMPPHandler.login("user1", "login")){
        				System.out.println("Connect & Anmeldung erfolgreich!");
        				connected = true;
        			} else {
        				System.out.println("Anmeldung fehlgeschlagen!");
        			}
        		} else {
        			System.out.println("Connect fehlgeschlagen!");
        		}
            }
        });
    }
    
	public void loadScene(String scene) {
		mySceneLoader.loadScene(scene);
	}
}
