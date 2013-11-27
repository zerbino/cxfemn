package client;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/rest")
public interface ServiceE {
	@POST
	@Path("/op")
	@Produces(MediaType.APPLICATION_XML)
	public String op(Etudiant e);

}
