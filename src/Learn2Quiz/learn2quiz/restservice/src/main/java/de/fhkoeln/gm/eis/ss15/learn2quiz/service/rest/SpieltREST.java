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

import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblspielt;



@Path("/spielt")
@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless


public class SpieltREST {
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
    public Response createspielt(Tblspielt spielt){
        if(spielt == null){
            throw new BadRequestException();
        }
        em.persist(spielt);
 
        //Build a uri with the spielt id appended to the absolute path
        //This is so the client gets the spielt id and also has the path to the resource created
        URI spieltUri = uriInfo.getAbsolutePathBuilder().path("").build();//URI spieltUri = uriInfo.getAbsolutePathBuilder().path(spielt.getId()).build();
 
        //The created response will not have a body. The spieltUri will be in the Header
        return Response.created(spieltUri).build();
    }
 
    @GET
    @Path("{id}")
    public Response getspielt(@PathParam("id") String id){
        Tblspielt spielt = em.find(Tblspielt.class, id);
 
        if(spielt == null){
            throw new NotFoundException();
        }
 
        return Response.ok(spielt).build();
    }
 
    //Response.ok() does not accept collections
    //But we return a collection and JAX-RS will generate header 200 OK and
    //will handle converting the collection to xml or json as the body
    @GET
    public Collection<Tblspielt> getspielts(){
        TypedQuery<Tblspielt> query = em.createNamedQuery("Tblspielt.findAll", Tblspielt.class);
        return query.getResultList();
    }
 
    @PUT
    @Path("{id}")
    public Response updatespielt(Tblspielt spielt, @PathParam("id") String id){
        if(id == null){
            throw new BadRequestException();
        }
 
        //Ideally we should check the id is a valid UUID. Not implementing for now
        // TODO
        //spielt.setIdSpielt(id);
        em.merge(spielt);
 
        return Response.ok().build();
    }
 
    @DELETE
    @Path("{id}")
    public Response deletespielt(@PathParam("id") String id){
        Tblspielt spielt = em.find(Tblspielt.class, id);
        if(spielt == null){
            throw new NotFoundException();
        }
        em.remove(spielt);
        return Response.noContent().build();
    }
	
}
