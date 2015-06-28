package de.fhkoeln.gm.eis.ss15.learn2quiz.service.rest;

import java.net.URI;
import java.util.Collection;
 








import java.util.Iterator;
import java.util.List;








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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.fhkoeln.gm.eis.ss15.learn2quiz.logic.GameHandler;
import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblspielsession;


@Path("/session")
@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class SessionREST {
    @PersistenceContext(unitName = "learn2quizPU")
    private EntityManager em;
 
    @Context
    private UriInfo uriInfo;
 
    @POST
    public Response createspielsession(Tblspielsession spielsession){
        if(spielsession == null){
            throw new BadRequestException();
        }
        em.persist(spielsession);
 
        URI spielsessionUri = uriInfo.getAbsolutePathBuilder().path(spielsession.getIdSpielsession()).build();
 
        return Response.created(spielsessionUri).build();
    }
 
    @GET
    @Path("{id}")
    public Response getspielsession(@PathParam("id") String id){
        Tblspielsession spielsession = em.find(Tblspielsession.class, id);
 
        if(spielsession == null){
            throw new NotFoundException();
        }
 
        return Response.ok(spielsession).build();
    }

    @GET
    public Collection<Tblspielsession> getspielsessions(){
        TypedQuery<Tblspielsession> query = em.createNamedQuery("Tblspielsession.findAll", Tblspielsession.class);
        return query.getResultList();
    }
 
    @PUT
    @Path("{id}")
    public Response updatespielsession(Tblspielsession spielsession, @PathParam("id") String id){
        if(id == null){
            throw new BadRequestException();
        }

        spielsession.setIdSpielsession(id);
        em.merge(spielsession);
 
        return Response.ok().build();
    }
 
    @DELETE
    @Path("{id}")
    public Response deletespielsession(@PathParam("id") String id){
        Tblspielsession spielsession = em.find(Tblspielsession.class, id);
        if(spielsession == null){
            throw new NotFoundException();
        }
        em.remove(spielsession);
        return Response.noContent().build();
    }
	
	
    
    @POST
    @Path("/start")
    // Start a new game session 
    public Response startSession(@QueryParam("init") String idUser, 
    		@QueryParam("grpid") String idGroup, 
    		@QueryParam("setid") String idCardset){

    	GameHandler myGameHandler = new GameHandler();
    	String mySessionId = myGameHandler.newSession(idUser, idGroup, idCardset);
    	if (mySessionId == null)
    		throw new ServerErrorException(500, new NullPointerException());
    	
        URI spielsessionUri = uriInfo.getAbsolutePathBuilder().path(mySessionId).build();
        return Response.created(spielsessionUri).build();
    }
	
  
}
