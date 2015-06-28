package de.fhkoeln.gm.eis.ss15.learn2quiz.logic;

import de.fhkoeln.gm.eis.ss15.learn2quiz.service.XMPPHandler;

public class GameHandler {
	public XMPPHandler myXMPPHandler;
	
	public GameHandler(){
		myXMPPHandler = new XMPPHandler();
		if (myXMPPHandler.connect("localhost", 5222)) {
			if (myXMPPHandler.login("server", "login")) {
				System.out.println("Connected from REST!");
			}
		}
		
		
	}
}
