package model;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;


@Path("/rest")
public interface Service {
	
	//TestClient4
	@POST
	@Path("/op")
	@Produces(MediaType.APPLICATION_XML)
	public String op(Personne p);
	/*
	@POST
	@Path("/ops")
	@Produces(MediaType.APPLICATION_XML)
	public List<Personne> op2( @Multipart("root") Personne p1, 
			@Multipart(value="personne2", required=false) Personne p2);*/
	
	//TestGreg
	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Path("/ops2")
	public String op(List<Personne> liste, Class<?> classe);
	//TestGreg
	@POST
	@Path("/getListe")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public List<Etudiant> opListe(Personne classe);

	//TestClient2
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_XML)
	public Etudiant op();
	

	//TestClient 3
	@GET
	@Path("/getPersonne/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String getPersonne(@PathParam("id") int id);
	//TestClient 3
	@GET
	@Path("/listePersonne")
	@Produces(MediaType.APPLICATION_XML)
	public String listePersonne();
	//TestClient 3
	@PUT
	@Path("/modificationNomPersonne/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String modificationNomPersonne(@PathParam("id") int id, String nom);
	//TestClient 3
	@PUT
	@Path("/modificationPrenomPersonne/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String modificationPrenomPersonne(@PathParam("id") int id, String nom);
	//TestClient 3
	@POST
	@Path("/ajouterPersonne")
	@Produces(MediaType.APPLICATION_XML)
	public String ajouterPersonne(Personne p);
	//TestClient 3
	@DELETE
	@Path("/effacerPersonne/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String effacerPersonne(@PathParam("id") int id);
	
	
}

