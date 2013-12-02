package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import models.Animal;
import models.Personne;

@Path("/rest")
public interface Service {
	@POST
	@Path("/op")
	@Produces(MediaType.APPLICATION_XML)
	public String op(Personne p);
	
	@POST
	@Path("/pseudo")
	@Produces(MediaType.APPLICATION_XML)
	public String pseudo(Animal a);

}
