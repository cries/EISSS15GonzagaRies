package de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.RESTHandler;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tblgruppe;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tbluser;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.xmpp.XMPPHandler;

public class GroupsStage extends Stage {
	
	public ResourceManager myResourceMgr;
	public SceneLoader mySceneLoader;
	public XMPPHandler myXMPPHandler;
	private Learn2Quiz myGame;
	private RESTHandler myRESTHandler;

	public GroupsStage(Learn2Quiz game) {
		myGame = game;
		myRESTHandler = myGame.myRESTHandler;
		myXMPPHandler = myGame.myXMPPHandler;
		myResourceMgr = myGame.myResourceHandler;
		mySceneLoader = myGame.mySceneLoader;
		loadScene("GroupsScreen");
		addActor(mySceneLoader.getRoot());
		
		SimpleButtonScript btnHome = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnBreadcrumbHome"));
		btnHome.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	myGame.showMainMenu();
            }
        });
		
		SimpleButtonScript btnBreadcrumbGroups = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnBreadcrumbGroups"));
		btnBreadcrumbGroups.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	//myGame.showMainMenu();
            }
        });
		
		SimpleButtonScript btnBack = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnBack"));
        btnBack.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	myGame.showMainMenu();
            }
        });
        
        
        SimpleButtonScript btnAdd = SimpleButtonScript.selfInit(mySceneLoader.getRoot().getCompositeById("btnAdd"));
        btnAdd.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
            	Tblgruppe newGroup = new Tblgruppe();
            	newGroup.setDtTitel("TI Gruppe");
            	newGroup.setDtBeschreibung("Technische Informatik");
            	newGroup.setDtTimestamp(new Timestamp(new Date().getTime()).toString());
            	Tbluser myUser = myRESTHandler.getUser("180E3E0E-3F10-4803-86B8-20C23B3E5903");
            	newGroup.setTbluser(myUser);
            	System.out.println("Statuscode :" + myRESTHandler.addGruppe(newGroup));
            	//List<Tblgruppe> myGruppen = myRESTHandler.getGruppen("180E3E0E-3F10-4803-86B8-20C23B3E5903");
            	
            	//System.out.println(myGruppen.toString());
            	
//            	Iterator<Tblgruppe> iterator = myGruppen.iterator();
//            	while (iterator.hasNext()){
//            	  Tblgruppe myGroup = (Tblgruppe) iterator.next();    
//            	  System.out.println("Titel: " + myGroup.getDtTitel() + " - " + myGroup.getDtBeschreibung());
//            	}
            }
        });
        
        
	}

	private void loadScene(String scene) {
		mySceneLoader.loadScene(scene);
	}

}
