package model;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@Path("/rest")
public interface Service {
	@POST
	@Path("/op")
	@Produces(MediaType.APPLICATION_XML)
	//@XmlJavaTypeAdapter(PersonneAdapter.class)
	public String op(@XmlJavaTypeAdapter(PersonneAdapter.class)Personne p);
	
	@GET
	@Path("/op1")
	//@XmlJavaTypeAdapter(EtudiantAdapter.class)
	@Produces(MediaType.APPLICATION_XML)
	public @XmlJavaTypeAdapter(EtudiantAdapter.class)Etudiant op1();
	
	@GET
	@Path("/op2")
	@Produces(MediaType.APPLICATION_XML)
	//@XmlJavaTypeAdapter(PersonneAdapter.class)
	public @XmlJavaTypeAdapter(PersonneAdapter.class)Personne op2();

}
