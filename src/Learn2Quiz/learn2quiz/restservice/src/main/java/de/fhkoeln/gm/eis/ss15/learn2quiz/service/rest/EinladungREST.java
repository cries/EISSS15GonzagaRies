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
	//the PersistenceContext annotation is a shortcut that hides the fact
    //that, an entity manager is always obtained from an EntityManagerFactory.
    //The peristitence.xml file defines persistence units which is supplied by name
    //to the EntityManagerFactory, thus  dictating settings and classes used by the
    //entity manager
    @PersistenceContext(unitName = "learn2quizPU")
    private EntityManager em;
 
    //Inject UriInfo to build the uri used in the POST response
    @Context
    private UriInfo uriInfo;
 
    @POST
    public Response createeinladung(Tbleinladung einladung){
        if(einladung == null){
            throw new BadRequestException();
        }
        em.persist(einladung);
 
        //Build a uri with the einladung id appended to the absolute path
        //This is so the client gets the einladung id and also has the path to the resource created
        URI einladungUri = uriInfo.getAbsolutePathBuilder().path(einladung.getIdEinladung()).build();
 
        //The created response will not have a body. The einladungUri will be in the Header
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
 
    //Response.ok() does not accept collections
    //But we return a collection and JAX-RS will generate header 200 OK and
    //will handle converting the collection to xml or json as the body
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
 
        //Ideally we should check the id is a valid UUID. Not implementing for now
        einladung.setIdEinladung(id);;
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
