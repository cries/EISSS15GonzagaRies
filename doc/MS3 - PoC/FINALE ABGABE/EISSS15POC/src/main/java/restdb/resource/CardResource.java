package restdb.resource;

import java.net.URI;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.ws.rs.core.*;
import javax.ws.rs.*;

import restdb.entities.Card;

@Path("/card")
@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class CardResource {
    @PersistenceContext(unitName = "PU")
    private EntityManager em;
 
    @Context
    private UriInfo uriInfo;
 
    // Neue Karte anlegen
    @POST
    @Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createCard(Card card){
        if(card == null){
            throw new BadRequestException();
        }
        // Neue Karte in die Datenbank eintragem
        em.persist(card);
        
        // URI zur neu erstellen Karte an den Client zurückschicken
        URI cardUri = uriInfo.getAbsolutePathBuilder().path(card.getId()).build();
        return Response.created(cardUri).build();
    }
 
    // Eine bestimmte Karte mit id zurückgeben
    @GET
    @Path("{id}")
    public Response getCard(@PathParam("id") String id){
        Card card = em.find(Card.class, id);
 
        if(card == null){
            throw new NotFoundException();
        }
 
        return Response.ok(card).build();
    }
 
    // Alle Karten zurückgeben
    @GET
    public Collection<Card> getCards(){
    	// SELECT * FROM table
        TypedQuery<Card> query = em.createNamedQuery("Card.findAll", Card.class);
        return query.getResultList();
    }
 
    // Karte verändern
    @PUT
    @Path("{id}")
    public Response updateCard(Card card, @PathParam("id") String id){
        if(id == null){
            throw new BadRequestException();
        }
        card.setId(id);
        // Karte mit der Karte in der Datenbank vereinen
        em.merge(card);
 
        return Response.ok().build();
    }
 
    // Karte löschen
    @DELETE
    @Path("{id}")
    public Response deleteCard(@PathParam("id") String id){
        Card card = em.find(Card.class, id);
        if(card == null){
            throw new NotFoundException();
        }
        // Karte in der Datenbank löschen
        em.remove(card);
        return Response.noContent().build();
    }
}
