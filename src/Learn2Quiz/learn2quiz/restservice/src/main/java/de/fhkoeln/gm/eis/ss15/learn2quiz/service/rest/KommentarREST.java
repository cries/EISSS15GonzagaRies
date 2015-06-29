package de.fhkoeln.gm.eis.ss15.learn2quiz.service.rest;

import java.net.URI;
import java.util.Collection;
 







import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.fhkoeln.gm.eis.ss15.learn2quiz.logic.GameHandler;
import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblkommentar;



@Path("/comment")
@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class KommentarREST {
	
    @PersistenceContext(unitName = "learn2quizPU")
    private EntityManager em;
    private GameHandler gameHandler = new GameHandler();
 
    @Context
    private UriInfo uriInfo;
 
    @POST
    public Response createkommentar(Tblkommentar kommentar){
        if(kommentar == null){
            throw new BadRequestException();
        }
        em.persist(kommentar);
        	
        gameHandler.notifyUserComment(kommentar);
       
        URI kommentarUri = uriInfo.getAbsolutePathBuilder().path(kommentar.getIdKommentar()).build();
 
        return Response.created(kommentarUri).build();
    }
 
    @GET
    @Path("{id}")
    public Response getkommentar(@PathParam("id") String id){
        Tblkommentar kommentar = em.find(Tblkommentar.class, id);
 
        if(kommentar == null){
            throw new NotFoundException();
        }
 
        return Response.ok(kommentar).build();
    }
 
    @DELETE
    @Path("{id}")
    public Response deletekommentar(@PathParam("id") String id){
        Tblkommentar kommentar = em.find(Tblkommentar.class, id);
        if(kommentar == null){
            throw new NotFoundException();
        }
        em.remove(kommentar);
        return Response.noContent().build();
    }

}
