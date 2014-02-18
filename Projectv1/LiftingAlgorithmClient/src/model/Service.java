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

//CLIENT
@Path("/rest")
public interface Service {
	//////////////// ACCES MEMOIRE
	@GET
	@Path("/getPersonnesMemoire")
	@Produces(MediaType.APPLICATION_XML)
	public List<PersonneImpl> getPersonnesMemoire();
	
	@GET
	@Path("/getPersonnesIntMemoire")
	@Produces(MediaType.APPLICATION_XML)
	public @XmlJavaTypeAdapter(PersonneAdapter.class)List<Personne> getPersonnesIntMemoire();
	
	/////////////////////////////////////// GET
	@GET
	@Path("/getPersonne")
	@Produces(MediaType.APPLICATION_XML)
	public PersonneImpl getPersonne();
		
	@GET
	@Path("/getPersonnes")
	@Produces(MediaType.APPLICATION_XML)
	public List<PersonneImpl> getPersonnes();
	
	@GET
	@Path("/getPersonneInt")
	@Produces(MediaType.APPLICATION_XML)
	public @XmlJavaTypeAdapter(PersonneAdapter.class)Personne getPersonneInt();

	@GET
	@Path("/getPersonnesInt")
	@Produces(MediaType.APPLICATION_XML)
	public @XmlJavaTypeAdapter(PersonneAdapter.class)List<Personne> getPersonnesInt();

	//////////////////////////////////////////POST
	@POST
	@Path("/postPersonne")
	@Produces(MediaType.APPLICATION_XML)
	public String postPersonne(EtudiantImpl p);
	
	@POST
	@Path("/postPersonnes")
	@Produces(MediaType.APPLICATION_XML)
	public String postPersonnes(List<EtudiantImpl> p);
	
	@POST
	@Path("/postPersonneInt")
	@Produces(MediaType.APPLICATION_XML)
	public String postPersonneInt(@XmlJavaTypeAdapter(PersonneAdapter.class)Etudiant p);
	
	@POST
	@Path("/postPersonnesInt")
	@Produces(MediaType.APPLICATION_XML)
	public String postPersonnesInt(@XmlJavaTypeAdapter(PersonneAdapter.class)List<Etudiant> p);
	
	@POST
	@Path("/opWithoutFields")
	@Produces(MediaType.APPLICATION_XML)
	public String opWithoutFields(EtudiantWithoutFields p);
	
	/*
	@POST
	@Path("/ops")
	@Produces(MediaType.APPLICATION_XML)
	public List<PersonneImpl> op(PersonneImpl p1, 
			PersonneImpl p2);
	
	@POST
	@Path("/opsInt")
	@Produces(MediaType.APPLICATION_XML)
	public List<Personne> opInt(@XmlJavaTypeAdapter(PersonneAdapter.class)Personne p1, 
			@XmlJavaTypeAdapter(PersonneAdapter.class)Personne p2);
			
	*/
	
	@GET
	@Path("/opget")
	@Produces(MediaType.TEXT_PLAIN)
	public String opGet(@QueryParam("")PersonneImpl personne);
	
	/*
	
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
	
	@POST
	@Path("/ajouterPersonne")
	@Produces(MediaType.APPLICATION_XML)
	public String ajouterPersonne(PersonneImpl p);
	
	@POST
	@Path("/ajouterPersonneInt")
	@Produces(MediaType.APPLICATION_XML)
	public String ajouterPersonneInt(@XmlJavaTypeAdapter(PersonneAdapter.class)Personne p);

	@DELETE
	@Path("/effacerPersonne/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String effacerPersonne(@PathParam("id") int id);
	*/

	

}

