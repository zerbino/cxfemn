package rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/rest")
public interface Service {
	@POST
	@Path("/op")
	@Produces(MediaType.APPLICATION_XML)
	public String op(Personne p);

}
