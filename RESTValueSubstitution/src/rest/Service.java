package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/rest")
public interface Service {
	@GET
	@Path("/op")
	@Produces(MediaType.APPLICATION_XML)
	public String op(@QueryParam("") Personne p);

}
