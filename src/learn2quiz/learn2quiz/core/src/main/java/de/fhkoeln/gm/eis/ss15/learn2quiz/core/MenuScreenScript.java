package de.fhkoeln.gm.eis.ss15.learn2quiz.core;

import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.script.IScript;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

public class MenuScreenScript implements IScript{

	private GameStage stage;
	private CompositeItem menu;
	private CompositeItem button;
	
	private SimpleButtonScript okButtonScript;

	public MenuScreenScript(GameStage stage) {
		this.stage = stage;
	}
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		okButtonScript.act(delta);
		if(okButtonScript.isDown()) {
			stage.close();
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		button.dispose();
		okButtonScript.dispose();
	}

	@Override
	public void init(CompositeItem item) {
		// TODO Auto-generated method stub
		menu = item;
		button = menu.getCompositeById("button");
		okButtonScript = new SimpleButtonScript();
		okButtonScript.init(button);
	}

}
