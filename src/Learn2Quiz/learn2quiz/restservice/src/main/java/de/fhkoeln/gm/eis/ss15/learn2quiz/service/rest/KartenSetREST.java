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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblkarteikarte;
import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblkartenset;




@Path("/cardset")
@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class KartenSetREST {
	
    @PersistenceContext(unitName = "learn2quizPU")
    private EntityManager em;
 
    @Context
    private UriInfo uriInfo;
 
    @POST
    public Response createkartenset(Tblkartenset kartenset){
        if(kartenset == null){
            throw new BadRequestException();
        }
        em.persist(kartenset);
 
        URI kartensetUri = uriInfo.getAbsolutePathBuilder().path(kartenset.getIdKartenset()).build();
 
        return Response.created(kartensetUri).build();
    }
 
    @GET
    @Path("{id}")
    public Response getkartenset(@PathParam("id") String id){
        Tblkartenset kartenset = em.find(Tblkartenset.class, id);
 
        if(kartenset == null){
            throw new NotFoundException();
        }
 
        return Response.ok(kartenset).build();
    }
    
    @GET
    @Path("{id}/cards")
    public Collection<Tblkarteikarte> getkarteikarten(@PathParam("id") String id){
        Tblkartenset mySet = em.find(Tblkartenset.class, id);
        return mySet.getTblkarteikartes();
    }
 
    @PUT
    @Path("{id}")
    public Response updatekartenset(Tblkartenset kartenset, @PathParam("id") String id){
        if(id == null){
            throw new BadRequestException();
        }
 
        
        kartenset.setIdKartenset(id);
        em.merge(kartenset);
 
        return Response.ok().build();
    }
 
    @DELETE
    @Path("{id}")
    public Response deletekartenset(@PathParam("id") String id){
        Tblkartenset kartenset = em.find(Tblkartenset.class, id);
        if(kartenset == null){
            throw new NotFoundException();
        }
        em.remove(kartenset);
        return Response.noContent().build();
    }
	
	
	
	
}
