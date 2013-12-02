package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.interceptor.InInterceptors;

import models.Personne;

@Path("/rest")
public interface Service {
	@POST
	@Path("/op")
	@Produces(MediaType.APPLICATION_XML)
	public String op(Personne p);

}
