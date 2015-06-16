package de.fhkoeln.gm.eis.ss15.learn2quiz.core.screens;

import com.badlogic.gdx.Screen;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.Learn2Quiz;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.GameStage;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.stages.InvitesStage;

public class InvitesScreen implements Screen {
	

	private InvitesStage myInvitesStage;
	private Learn2Quiz myGame;
	
	public InvitesScreen(Learn2Quiz myGame) {
		myInvitesStage = new InvitesStage(myGame);
		myGame.myInputMultiplexer.addProcessor(myInvitesStage);
	}

	@Override
	public void render(float delta) {
		myInvitesStage.act(delta);
		myInvitesStage.draw();
		
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
		myInvitesStage.dispose();		
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
		myInvitesStage.dispose();
		
	}
}
