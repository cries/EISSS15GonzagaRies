package de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.xmpp.XMPPHandler;


public class InvitesStage extends Stage {

		public ResourceManager myResourceMgr;
		public SceneLoader mySceneLoader;
		public XMPPHandler myXMPPHandler;
		private Learn2Quiz myGame;
	
	
	
	public InvitesStage(Learn2Quiz game) {
			myGame = game;
			//myRESTHandler = myGame.RESTHandler;
			myXMPPHandler = myGame.myXMPPHandler;
			myResourceMgr = myGame.myResourceHandler;
			mySceneLoader = myGame.mySceneLoader;
			loadScene("GroupsScreen");
			addActor(mySceneLoader.getRoot());
			}



	private void loadScene(String scene) {
		mySceneLoader.loadScene(scene);
		
	}

}
