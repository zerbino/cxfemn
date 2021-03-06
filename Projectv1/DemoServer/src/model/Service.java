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

@Path("/rest")
public interface Service {
	
	@AllowSubstitution
	@POST
	@Path("/op")
	@Produces(MediaType.APPLICATION_XML)
	public String op(PersonImpl p);
	
	@AllowSubstitution
	@POST
	@Path("/opInt")
	@Produces(MediaType.APPLICATION_XML)
	public String opInt(@XmlJavaTypeAdapter(PersonAdapter.class)Person p);
	
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_XML)
	public StudentImpl op();
	
	@GET
	@Path("/getInt")
	@Produces(MediaType.APPLICATION_XML)
	public @XmlJavaTypeAdapter(StudentAdapter.class)Student opInt();

}

