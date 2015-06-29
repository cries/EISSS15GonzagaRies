package de.fhkoeln.gm.eis.ss15.learn2quiz.logic;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;

import de.fhkoeln.gm.eis.ss15.learn2quiz.service.XMPPHandler;
import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblgruppe;
import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.TblistTeil;
import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblkarteikarte;
import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblkartenset;
import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblkommentar;
import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblspielsession;
import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblspielt;
import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tbluser;

public class GameHandler {
	@PersistenceContext(unitName = "learn2quizPU")
    private EntityManager em;
	
	public XMPPHandler myXMPPHandler;
	
	/**
	 * Create a new GameHandler
	 * Connects to XMPP Server
	 */
	public GameHandler(){
		myXMPPHandler = new XMPPHandler();
		if (myXMPPHandler.connect("localhost", 5222)) {
			if (myXMPPHandler.login("server", "login")) {
				System.out.println("Connected from REST!");
			}
		}	
	}
	
	/**
	 * Creates a new game session and invites other players from the same group
	 * Players are chosen by their ranking. The current setting matches players 
	 * who are in 33% +/- range of the initiator's ranking.
	 * After matching the players, they're invited by an asynchronous notification
	 * 
	 * @param idUser The session initiator	
	 * @param idGroup The group to play in
	 * @param idCardset The cardset that will be played
	 * @return Session ID or null
	 */
	public String newSession(String idUser, String idGroup, String idCardset) {
		int initRanking;
		// Tolerance parameter for the ranking (+/-)
        final double rankingFactor = 0.33;
        List<Tbluser> myPlayers = null;
        Tblspielsession newSession;
    	
        // Get initiator 
    	Tbluser initiator = em.find(Tbluser.class, idUser);
    	// Get the user's ranking points
    	initRanking = initiator.getDtPunktzahl();
    	// Get all users from the group
    	Collection<TblistTeil> groupUsers = getUserByGroup(idGroup);
    	Iterator<TblistTeil> it = groupUsers.iterator();
    	while (it.hasNext()) {
    		TblistTeil row = it.next();
    		int playerRanking = row.getTbluser().getDtPunktzahl();
			if(((playerRanking) >= (initRanking-(initRanking*rankingFactor)))
				&& (playerRanking <= (initRanking+(initRanking*rankingFactor)))) 
			{
				// User matches the initiator's ranking
				myPlayers.add(row.getTbluser());
			}
    	}
    	
    	if(myPlayers.size() < 1) {
    		// TODO
    		return null;
    	}
    	
    	newSession = new Tblspielsession();
    	newSession.setTblkartenset(em.find(Tblkartenset.class, idCardset));
    	em.persist(newSession);
    	Iterator<Tbluser> it1 = myPlayers.iterator();
    	while (it1.hasNext()) {
    		Tbluser player = it1.next();
    		// Send asynchronous game invitation to the matched players
    		myXMPPHandler.publishItemPayload(player.getIdUser(), "gameinvite", 
    				"<gameinvite><user>" + player.getIdUser() + "</user><session>" 
    						+ newSession.getIdSpielsession() + "</session></gameinvite>");
    	}
    	return newSession.getIdSpielsession();
	}
	
	/**
	 * Notifies users upon entry of a new comment
	 * Notifications are sent to all users in the group (broadcast)
	 * The message includes the comment author & the involved card question
	 * 
	 * @param newComment The newly created comment of type Tblkommentar
	 */
	public void notifyUserComment(Tblkommentar newComment){
		// Extract card object from the comment object
		Tblkarteikarte myCard = newComment.getTblkarteikarte();
		// Extract group object from the card object
		Tblgruppe myGroup = newComment.getTblkarteikarte().getTblkartenset().getTblgruppe();
		
		// Send asynchronous broadcast message to users in the concerned group 
		myXMPPHandler.publishItemPayload(myGroup.getIdGruppe() + "_comments", "newcomment" , 
				"<newcomment><author>" + newComment.getTbluser().getIdUser() + "</author>"
						+ "<cardquestion>" + myCard.getDtFrage() + "</cardquestion></newcomment>");
		
	}

	/**
	 * Returns a list of all users in group idGroup
	 * @param idGroup the group id
	 * @return Collection of TblistTeil
	 */
    public Collection<TblistTeil> getUserByGroup(String idGroup){
        TypedQuery<TblistTeil> query = em.createNamedQuery("istteil.findUsersByGroup", TblistTeil.class);
        return query.getResultList();
    }
}
