package model;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import annotations.AllowSubstitution;
//import filter.server.AdjustClient;
//SERVEUR
@Path("/rest")
public interface Service {
	
	@GET
	@Path("/opget")
	@Produces(MediaType.TEXT_PLAIN)
	public String opGet(@QueryParam("")PersonneImpl personne);
	
	@AllowSubstitution
	@POST
	@Path("/opWithoutFields")
	@Produces(MediaType.APPLICATION_XML)
	public String opWithoutFields(PersonneWithoutFields p);

	@AllowSubstitution
	@POST
	@Path("/op")
	@Produces(MediaType.APPLICATION_XML)
	public String op(PersonneImpl p);
	
	@AllowSubstitution
	@POST
	@Path("/opInt")
	@Produces(MediaType.APPLICATION_XML)
	public String opInt(@XmlJavaTypeAdapter(PersonneAdapter.class)Personne p);
	
	@AllowSubstitution
	@POST
	@Path("/ops")
	@Produces(MediaType.APPLICATION_XML)
	public List<PersonneImpl> op(PersonneImpl p1, 
			PersonneImpl p2);
	
	@AllowSubstitution
	@POST
	@Path("/opsInt")
	@Produces(MediaType.APPLICATION_XML)
	public List<Personne> opInt(@XmlJavaTypeAdapter(PersonneAdapter.class)Personne p1, 
			@XmlJavaTypeAdapter(PersonneAdapter.class)Personne p2);
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_XML)
	public EtudiantImpl op();
	
	@GET
	@Path("/getInt")
	@Produces(MediaType.APPLICATION_XML)
	public @XmlJavaTypeAdapter(EtudiantAdapter.class)Etudiant opInt();
	
	
	@GET
	@Path("/getPersonne/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String getPersonne(@PathParam("id") int id);
	
	@GET
	@Path("/listePersonne")
	@Produces(MediaType.APPLICATION_XML)
	public String listePersonne();
	
	@PUT
	@Path("/modificationNomPersonne/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String modificationNomPersonne(@PathParam("id") int id, String nom);
	
	@PUT
	@Path("/modificationPrenomPersonne/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String modificationPrenomPersonne(@PathParam("id") int id, String nom);
	
	@AllowSubstitution
	@POST
	@Path("/ajouterPersonne")
	@Produces(MediaType.APPLICATION_XML)
	public String ajouterPersonne(PersonneImpl p);
	
	@AllowSubstitution
	@POST
	@Path("/ajouterPersonneInt")
	@Produces(MediaType.APPLICATION_XML)
	public String ajouterPersonneInt(@XmlJavaTypeAdapter(PersonneAdapter.class)Personne p);

	@DELETE
	@Path("/effacerPersonne/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String effacerPersonne(@PathParam("id") int id);
	
	@GET
	@Path("/getPersonne")
	@Produces(MediaType.APPLICATION_XML)
	public List<PersonneImpl> getPersonnes();
	
	@GET
	@Path("/getPersonneInt")
	@Produces(MediaType.APPLICATION_XML)
	public @XmlJavaTypeAdapter(PersonneAdapter.class)List<Personne> getPersonnesInt();
	

}

