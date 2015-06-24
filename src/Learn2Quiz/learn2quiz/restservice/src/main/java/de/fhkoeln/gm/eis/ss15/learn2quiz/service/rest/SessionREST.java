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

import de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities.Tblspielsession;


@Path("/session")
@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class SessionREST {
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
    public Response createspielsession(Tblspielsession spielsession){
        if(spielsession == null){
            throw new BadRequestException();
        }
        em.persist(spielsession);
 
        //Build a uri with the spielsession id appended to the absolute path
        //This is so the client gets the spielsession id and also has the path to the resource created
        URI spielsessionUri = uriInfo.getAbsolutePathBuilder().path(spielsession.getIdSpielsession()).build();
 
        //The created response will not have a body. The spielsessionUri will be in the Header
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
 
    //Response.ok() does not accept collections
    //But we return a collection and JAX-RS will generate header 200 OK and
    //will handle converting the collection to xml or json as the body
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
 
        //Ideally we should check the id is a valid UUID. Not implementing for now
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
	
	
	
	
	
	
}
