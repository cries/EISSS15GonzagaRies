package de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
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
	private Learn2Quiz myGame;
	private Boolean connected = false;
	private TextField txtUsername;
	private TextField txtPassword;
	
    public LoginStage(Learn2Quiz game) {
    	myGame = game;
		myXMPPHandler = myGame.myXMPPHandler;
		myResourceMgr = myGame.myResourceHandler;
		mySceneLoader = myGame.mySceneLoader;
		loadScene("LoginScene");
		addActor(mySceneLoader.getRoot());
		
		txtUsername = new TextField("", game.mySkin);
    	txtUsername.setMessageText("Benutzername");
    	txtUsername.setWidth(452);
    	txtUsername.setHeight(40);
    	txtUsername.setPosition(286, 446);
    	addActor(txtUsername);
    	
    	txtPassword = new TextField("", game.mySkin);
    	txtPassword.setMessageText("Passwort");
    	txtPassword.setPasswordCharacter('*');
    	txtPassword.setPasswordMode(true);
    	txtPassword.setWidth(452);
    	txtPassword.setHeight(40);
    	txtPassword.setPosition(286, 364);
    	addActor(txtPassword);
		
		SimpleButtonScript btnLogin = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnLogin"));
        btnLogin.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	if (!connected && myXMPPHandler.connect("localhost", 5222)){
        			if (myXMPPHandler.login(txtUsername.getText(), txtPassword.getText())){
        				System.out.println("Connect & Anmeldung erfolgreich!");
        				connected = true;
        				myGame.showMainMenu();
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