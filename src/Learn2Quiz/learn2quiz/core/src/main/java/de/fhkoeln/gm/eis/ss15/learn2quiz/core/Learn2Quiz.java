package de.fhkoeln.gm.eis.ss15.learn2quiz.core;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens.GroupsScreen;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens.LoginScreen;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens.MainMenuScreen;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens.RegisterScreen;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens.WelcomeScreen;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.LoginStage;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.WelcomeStage;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.xmpp.XMPPHandler;

public class Learn2Quiz extends Game {
	
	public XMPPHandler myXMPPHandler;
	public ResourceManager myResourceHandler;
	
	public final String title = "Learn2Quiz";
	public final String version = "v0.1";
	
	public WelcomeScreen myWelcomeScreen;
	public LoginScreen myLoginScreen;
	public RegisterScreen myRegisterScreen;
	public MainMenuScreen myMainMenuScreen;
	public GroupsScreen myGroupsScreen;
	
	public InputMultiplexer myInputMultiplexer;
	public SceneLoader mySceneLoader;
	
	public Skin mySkin;
	
	@Override
	public void create () {
		myXMPPHandler = new XMPPHandler();
		myResourceHandler = new ResourceManager();
		myInputMultiplexer = new InputMultiplexer();
		mySceneLoader = new SceneLoader(myResourceHandler);
		myResourceHandler.initAllResources();
		mySkin = new Skin( Gdx.files.internal( "uiskin.json" ));
		
		myWelcomeScreen = new WelcomeScreen(this);
		//myLoginScreen = new LoginScreen(this);
		setScreen(myWelcomeScreen);
	}

	@Override
	public void resize (int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void pause () {
		super.pause();
	}

	@Override
	public void resume () {
		super.resume();
	}

	@Override
	public void dispose () {
		super.dispose();
	}
	
	public void showLogin() {
		myLoginScreen = new LoginScreen(this);
		setScreen(myLoginScreen);
	}
	
	public void showRegister() {
		myRegisterScreen = new RegisterScreen(this);
		setScreen(myRegisterScreen);
	}
	
	public void showMainMenu() {
		myMainMenuScreen = new MainMenuScreen(this);
		setScreen(myMainMenuScreen);
	}
	
	public void showWelcome() {
		myWelcomeScreen = new WelcomeScreen(this);
		setScreen(myWelcomeScreen);
	}
	
	public void showGroups() {
		myGroupsScreen = new GroupsScreen(this);
		setScreen(myGroupsScreen);
	}
}
