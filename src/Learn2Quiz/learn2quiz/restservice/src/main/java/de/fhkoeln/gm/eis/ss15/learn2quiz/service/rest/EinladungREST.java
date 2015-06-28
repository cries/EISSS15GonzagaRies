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

import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tbleinladung;


@Path("/invite")
@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class EinladungREST {
    @PersistenceContext(unitName = "learn2quizPU")
    private EntityManager em;
 
    @Context
    private UriInfo uriInfo;
 
    @POST
    public Response createeinladung(de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tbleinladung einladung){
        if(einladung == null){
            throw new BadRequestException();
        }
        em.persist(einladung);
 
        URI einladungUri = uriInfo.getAbsolutePathBuilder().path(einladung.getIdEinladung()).build();
 
        return Response.created(einladungUri).build();
    }
 
    @GET
    @Path("{id}")
    public Response geteinladung(@PathParam("id") String id){
        Tbleinladung einladung = em.find(Tbleinladung.class, id);
 
        if(einladung == null){
            throw new NotFoundException();
        }
 
        return Response.ok(einladung).build();
    }

    @GET
    public Collection<Tbleinladung> geteinladungs(){
        TypedQuery<Tbleinladung> query = em.createNamedQuery("einladung.findAll", Tbleinladung.class);
        return query.getResultList();
    }
 
    @PUT
    @Path("{id}")
    public Response updateeinladung(Tbleinladung einladung, @PathParam("id") String id){
        if(id == null){
            throw new BadRequestException();
        }

        einladung.setIdEinladung(id);
        em.merge(einladung);
 
        return Response.ok().build();
    }
 
    @DELETE
    @Path("{id}")
    public Response deleteeinladung(@PathParam("id") String id){
        Tbleinladung einladung = em.find(Tbleinladung.class, id);
        if(einladung == null){
            throw new NotFoundException();
        }
        em.remove(einladung);
        return Response.noContent().build();
    }
}
