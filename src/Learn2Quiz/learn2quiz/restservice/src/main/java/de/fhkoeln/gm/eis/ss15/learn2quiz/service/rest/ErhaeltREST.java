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

import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblerhaelt;
import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.TblerhaeltPK;


@Path("/erhaelt")
@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless


public class ErhaeltREST {
    @PersistenceContext(unitName = "learn2quizPU")
    private EntityManager em;

    @Context
    private UriInfo uriInfo;
 
    @POST
    public Response createerhaelt(Tblerhaelt erhaelt){
        if(erhaelt == null){
            throw new BadRequestException();
        }
        em.persist(erhaelt);
 
        URI erhaeltUri = uriInfo.getAbsolutePathBuilder().path("").build();
 
        return Response.created(erhaeltUri).build();
    }
 
    @GET
    @Path("{id}")
    public Response geterhaelt(@PathParam("id") String id){
        Tblerhaelt erhaelt = em.find(Tblerhaelt.class, id);
 
        if(erhaelt == null){
            throw new NotFoundException();
        }
 
        return Response.ok(erhaelt).build();
    }

    @GET
    public Collection<Tblerhaelt> geterhaelts(){
        TypedQuery<Tblerhaelt> query = em.createNamedQuery("Tblerhaelt.findAll", Tblerhaelt.class);
        return query.getResultList();
    }
 
    @PUT
    @Path("{id}")
    public Response updateerhaelt(Tblerhaelt erhaelt, @PathParam("id") String id){
        if(id == null){
            throw new BadRequestException();
        }

        erhaelt.setId(new TblerhaeltPK()); //erhaelt.setId(id);
        em.merge(erhaelt);
 
        return Response.ok().build();
    }
 
    @DELETE
    @Path("{id}")
    public Response deleteerhaelt(@PathParam("id") String id){
        Tblerhaelt erhaelt = em.find(Tblerhaelt.class, id);
        if(erhaelt == null){
            throw new NotFoundException();
        }
        em.remove(erhaelt);
        return Response.noContent().build();
    }
	
}
