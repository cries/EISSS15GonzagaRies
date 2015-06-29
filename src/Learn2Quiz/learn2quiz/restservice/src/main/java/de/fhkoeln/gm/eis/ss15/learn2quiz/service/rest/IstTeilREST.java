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

import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.TblistTeil;
import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblspielsession;


@Path("/istteil")
@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class IstTeilREST {
    @PersistenceContext(unitName = "learn2quizPU")
    private EntityManager em;
 
    @Context
    private UriInfo uriInfo;
 
    @POST
    public Response createistteil(TblistTeil istteil){
        if(istteil == null){
            throw new BadRequestException();
        }
        em.persist(istteil);
 
        URI istteilUri = uriInfo.getAbsolutePathBuilder().path("").build();//uriInfo.getAbsolutePathBuilder().path(istteil.getId()).build();
 
        return Response.created(istteilUri).build();
    }
 
    @GET
    @Path("{id}")
    public Response getistteil(@PathParam("id") String id){
        TblistTeil istteil = em.find(TblistTeil.class, id);
 
        if(istteil == null){
            throw new NotFoundException();
        }
 
        return Response.ok(istteil).build();
    }
 
    @GET
    public Collection<TblistTeil> getistteils(){
        TypedQuery<TblistTeil> query = em.createNamedQuery("istteil.findAll", TblistTeil.class);
        return query.getResultList();
    }
 
    @PUT
    @Path("{id}")
    public Response updateistteil(TblistTeil istteil, @PathParam("id") String id){
        if(id == null){
            throw new BadRequestException();
        }
 
        em.merge(istteil);
 
        return Response.ok().build();
    }
 
    @DELETE
    @Path("{id}")
    public Response deleteistteil(@PathParam("id") String id){
        TblistTeil istteil = em.find(TblistTeil.class, id);
        if(istteil == null){
            throw new NotFoundException();
        }
        em.remove(istteil);
        return Response.noContent().build();
    }

    
    
    
}
