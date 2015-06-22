package de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.XMPPConnection;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.RESTHandler;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tbluser;
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
	private RESTHandler myRESTHandler;
	
	public RegisterStage(Learn2Quiz game) {
		myGame = game;
		myRESTHandler = myGame.myRESTHandler;
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
    	txtPassword.setPasswordMode(false);
    	txtPassword.setWidth(452);
    	txtPassword.setHeight(40);
    	txtPassword.setPosition(286, 526);
    	addActor(txtPassword);
    	
    	txtValPassword = new TextField("", game.mySkin);
    	txtValPassword.setMessageText("Passwort wiederholen");
    	txtValPassword.setPasswordCharacter('*');
    	txtValPassword.setPasswordMode(false);
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
            	if ((!txtUsername.getText().equals(""))
            			&& (!txtPassword.getText().equals(""))
            			&& (!txtValPassword.getText().equals(""))
            			&& (!txtEmail.getText().equals(""))) {
            		System.out.println(txtPassword.getText() + " = " + txtValPassword.getText());
            		if (txtPassword.getText().equals(txtValPassword.getText())) {
            			Tbluser myUser = new Tbluser();
            			//myUser.setIdUser(txtUsername.getText());
            			myUser.setDtBenutzername(txtUsername.getText());
            			myUser.setDtEmail(txtEmail.getText());
            			myUser.setDtPasswort(txtPassword.getText());
                    	System.out.println("Response Code: " + myRESTHandler.addUser(myUser));
                    	try {
                    		if (myXMPPHandler.isConnected()) {
                    			myXMPPHandler.createAccount(txtUsername.getText(), txtPassword.getText());
                    		} else {
                    			System.out.println("NOT connected to XMPP!");
                    		}
                    	} catch (Exception e) {
                    		throw new RuntimeException(e);
                    	}
            		} else {
            			System.out.println("Passwoerter stimmen nicht ueberein!");
            		}
            	} else {
            		System.out.println("Alle Felder ausfuellen!");
            	}
            	
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
