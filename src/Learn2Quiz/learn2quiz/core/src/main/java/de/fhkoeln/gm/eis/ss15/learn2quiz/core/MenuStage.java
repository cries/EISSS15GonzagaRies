package de.fhkoeln.gm.eis.ss15.learn2quiz.core;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

public class MenuStage extends Stage {
	
	private SceneLoader sl;
	
    public MenuStage(final GameStage myGameStage) {
 
        //Creating a scene loader and passing it a resource manager of game stage
        this.sl = new SceneLoader(myGameStage.rm);
 
        // loading UI scene
        sl.loadScene("MenuScene");
 
        // adding it's root composite item to the stage
        addActor(sl.getRoot());
 
        // Creating restart button, and adding a click listener to it
        SimpleButtonScript btnEinloggen = SimpleButtonScript.selfInit(sl.getRoot().getCompositeById("btnEinloggen"));
        btnEinloggen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                //restarting game when clicked
                //gameStage.restart();
            	System.out.println("Click: Einloggen");
            }
        });
        
        SimpleButtonScript btnRegistrieren = SimpleButtonScript.selfInit(sl.getRoot().getCompositeById("btnRegistrieren"));
        btnRegistrieren.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                //restarting game when clicked
                //gameStage.restart();
            	System.out.println("Click: Registrieren");
            }
        });
    }
}
