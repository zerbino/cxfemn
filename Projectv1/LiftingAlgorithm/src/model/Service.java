package model;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/rest")
public interface Service {
	@POST
	@Path("/op")
	@Produces(MediaType.APPLICATION_XML)
	public String op(Personne p);
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_XML)
	public Etudiant op();
	
	@GET
	@Path("/getPersonne/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String getPersonne(@PathParam("id") int id);
	
	@GET
	@Path("/listePersonne")
	@Produces(MediaType.APPLICATION_XML)
	public String listePersonne();
	
	@PUT
	@Path("/modificationPersonne/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String modificationNomPersonne(@PathParam("id") int id, String nom);
	
	@PUT
	@Path("/modificationPersonne/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String modificationPrenomPersonne(@PathParam("id") int id, String nom);
	
	@POST
	@Path("/ajouterPersonne")
	@Produces(MediaType.APPLICATION_XML)
	public String ajouterPersonne(Personne p);

	@DELETE
	@Path("/effacerPersonne/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String effacerPersonne(@PathParam("id") int id);
}

