package de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens;

import com.badlogic.gdx.Screen;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.GroupsStage;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.LoginStage;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.MainMenuStage;

public class GroupsScreen implements Screen {

	private GroupsStage myGroupsStage;
	
	public GroupsScreen(Learn2Quiz myGame) {
		myGroupsStage = new GroupsStage(myGame);
		myGame.myInputMultiplexer.addProcessor(myGroupsStage);
		//Gdx.input.setInputProcessor(myGame.myInputMultiplexer);
	}

	@Override
	public void render(float delta) {
		myGroupsStage.act(delta);
		myGroupsStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		myGroupsStage.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		myGroupsStage.dispose();
	}
}
