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



@Path("/card")
@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class KarteikarteREST {

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
    public Response createkarteikarte(Tblkarteikarte karteikarte){
        if(karteikarte == null){
            throw new BadRequestException();
        }
        em.persist(karteikarte);
 
        //Build a uri with the karteikarte id appended to the absolute path
        //This is so the client gets the karteikarte id and also has the path to the resource created
        URI karteikarteUri = uriInfo.getAbsolutePathBuilder().path(karteikarte.getIdKarteikarte()).build();
 
        //The created response will not have a body. The karteikarteUri will be in the Header
        return Response.created(karteikarteUri).build();
    }
 
    @GET
    @Path("{id}")
    public Response getkarteikarte(@PathParam("id") String id){
        Tblkarteikarte karteikarte = em.find(Tblkarteikarte.class, id);
 
        if(karteikarte == null){
            throw new NotFoundException();
        }
 
        return Response.ok(karteikarte).build();
    }
 
    //Response.ok() does not accept collections
    //But we return a collection and JAX-RS will generate header 200 OK and
    //will handle converting the collection to xml or json as the body
    @GET
    public Collection<Tblkarteikarte> getkarteikartes(){
        TypedQuery<Tblkarteikarte> query = em.createNamedQuery("Tblkarteikarte.findAll", Tblkarteikarte.class);
        return query.getResultList();
    }
    
    
 
    @PUT
    @Path("{id}")
    public Response updatekarteikarte(Tblkarteikarte karteikarte, @PathParam("id") String id){
        if(id == null){
            throw new BadRequestException();
        }
 
        //Ideally we should check the id is a valid UUID. Not implementing for now
        karteikarte.setIdKarteikarte(id);
        em.merge(karteikarte);
 
        return Response.ok().build();
    }
 
    @DELETE
    @Path("{id}")
    public Response deletekarteikarte(@PathParam("id") String id){
        Tblkarteikarte karteikarte = em.find(Tblkarteikarte.class, id);
        if(karteikarte == null){
            throw new NotFoundException();
        }
        em.remove(karteikarte);
        return Response.noContent().build();
    }
	
}
