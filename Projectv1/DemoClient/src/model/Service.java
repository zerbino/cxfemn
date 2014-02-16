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
		
	@POST
	@Path("/op")
	@Produces(MediaType.APPLICATION_XML)
	public String op(EtudiantImpl p);
	
	@POST
	@Path("/opInt")
	@Produces(MediaType.APPLICATION_XML)
	public String opInt(@XmlJavaTypeAdapter(PersonneAdapter.class)Personne p);
			
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_XML)
	public PersonneImpl op();
	
	@GET
	@Path("/getInt")
	@Produces(MediaType.APPLICATION_XML)
	public @XmlJavaTypeAdapter(PersonneAdapter.class)Personne opInt();
	
}

