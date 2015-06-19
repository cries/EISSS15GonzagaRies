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
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.RESTHandler;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tbluser;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.xmpp.XMPPHandler;

public class LoginStage extends Stage {
	
	public ResourceManager myResourceMgr;
	public SceneLoader mySceneLoader;
	public XMPPHandler myXMPPHandler;
	public RESTHandler myRESTHandler;
	private Learn2Quiz myGame;
	private Boolean connected = false;
	private TextField txtUsername;
	private TextField txtPassword;
	
    public LoginStage(Learn2Quiz game) {
    	myGame = game;
		myXMPPHandler = myGame.myXMPPHandler;
		myResourceMgr = myGame.myResourceHandler;
		mySceneLoader = myGame.mySceneLoader;
		myRESTHandler = myGame.myRESTHandler;
		loadScene("LoginScreen");
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
            	if (myXMPPHandler.isConnected()) {
        			if (myXMPPHandler.login(txtUsername.getText(), txtPassword.getText())){
        				System.out.println("Connect & Anmeldung erfolgreich!");
        				connected = true;
        				myGame.showMainMenu();
        			} else {
        				System.out.println("Anmeldung fehlgeschlagen!");
        			}
        		} else {
        			System.out.println("NOT connected to XMPP!");
        		}
        			
//        			// GET USER BY ID
        			Tbluser myUser = myRESTHandler.getUser("1");
//        			System.out.println("Name: " + myUser.getDtBenutzername() + " - Email: " + myUser.getDtEmail());
        			
//					// ADD NEW USER        			
//        			Tbluser myNewUser = new Tbluser();
//        			myNewUser.setDtBenutzername("TestUser");
//        			myNewUser.setDtEmail("user@email.com");
//        			myNewUser.setDtPasswort("login");
//        			myNewUser.setDtPunktzahl(0);
//        			myRESTHandler.addUser(myNewUser);
        			
					// UPDATE
        			
 
            }
        });
        
        SimpleButtonScript btnBack = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnBack"));
        btnBack.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	myGame.showWelcome();
            }
        });
    }
    
	public void loadScene(String scene) {
		mySceneLoader.loadScene(scene);
	}
}
