package de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.xmpp.XMPPHandler;

public class RegisterStage extends Stage {
	public ResourceManager myResourceMgr;
	public SceneLoader mySceneLoader;
	public XMPPHandler myXMPPHandler;
	private Learn2Quiz myGame;
	private Boolean connected = false;
	private TextField txtUsername;
	private TextField txtPassword;
	private TextField txtValPassword;
	private TextField txtEmail;
	
	public RegisterStage(Learn2Quiz game) {
		myGame = game;
		myXMPPHandler = myGame.myXMPPHandler;
		myResourceMgr = myGame.myResourceHandler;
		mySceneLoader = myGame.mySceneLoader;
		loadScene("RegisterScreen");
		addActor(mySceneLoader.getRoot());
		
		txtUsername = new TextField("", game.mySkin);
    	txtUsername.setMessageText("Benutzername");
    	txtUsername.setWidth(452);
    	txtUsername.setHeight(40);
    	txtUsername.setPosition(286, 608);
    	addActor(txtUsername);
    	
    	txtPassword = new TextField("", game.mySkin);
    	txtPassword.setMessageText("Passwort");
    	txtPassword.setPasswordCharacter('*');
    	txtPassword.setPasswordMode(true);
    	txtPassword.setWidth(452);
    	txtPassword.setHeight(40);
    	txtPassword.setPosition(286, 526);
    	addActor(txtPassword);
    	
    	txtValPassword = new TextField("", game.mySkin);
    	txtValPassword.setMessageText("Passwort wiederholen");
    	txtValPassword.setPasswordCharacter('*');
    	txtValPassword.setPasswordMode(true);
    	txtValPassword.setWidth(452);
    	txtValPassword.setHeight(40);
    	txtValPassword.setPosition(286, 446);
    	addActor(txtValPassword);
    	
    	txtEmail = new TextField("", game.mySkin);
    	txtEmail.setMessageText("Email-Adresse");
    	txtEmail.setWidth(452);
    	txtEmail.setHeight(40);
    	txtEmail.setPosition(286, 364);
    	addActor(txtEmail);
		
		SimpleButtonScript btnRegister = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnRegister"));
        btnRegister.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	myGame.showMainMenu();
            }
        });
        
        SimpleButtonScript btnBack = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnBack"));
        btnBack.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	myGame.showWelcome();
            }
        });
	}

	private void loadScene(String scene) {
		mySceneLoader.loadScene(scene);
	}

}
