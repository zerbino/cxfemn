package serveur;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import filter.ChangeNameBinding;

public interface Service {
	@Path("personne")
	@ChangeNameBinding
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getPersonne();

}
