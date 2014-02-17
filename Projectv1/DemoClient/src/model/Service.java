package model;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

//CLIENT
@Path("/rest")
public interface Service {
		
	@POST
	@Path("/op")
	@Produces(MediaType.APPLICATION_XML)
	public String op(StudentImpl p);
	
	@POST
	@Path("/opInt")
	@Produces(MediaType.APPLICATION_XML)
	public String opInt(@XmlJavaTypeAdapter(PersonAdapter.class)Person p);
			
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_XML)
	public PersonImpl op();
	
	@GET
	@Path("/getInt")
	@Produces(MediaType.APPLICATION_XML)
	public @XmlJavaTypeAdapter(PersonAdapter.class)Person opInt();
	
}

