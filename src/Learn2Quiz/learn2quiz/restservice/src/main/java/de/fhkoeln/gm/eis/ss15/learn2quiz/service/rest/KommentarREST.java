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

import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblkommentar;



@Path("/comment")
@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class KommentarREST {
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
    public Response createkommentar(Tblkommentar kommentar){
        if(kommentar == null){
            throw new BadRequestException();
        }
        em.persist(kommentar);
 
        //Build a uri with the kommentar id appended to the absolute path
        //This is so the client gets the kommentar id and also has the path to the resource created
        URI kommentarUri = uriInfo.getAbsolutePathBuilder().path(kommentar.getIdKommentar()).build();
 
        //The created response will not have a body. The kommentarUri will be in the Header
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
