package filter;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;

import serverLifter.Lifter;

@PreMatching
public class ClientRequestFilter implements ContainerRequestFilter,ContainerResponseFilter {

	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		/*Cette partie là permet d'intercepter à l'entrée de la requête
		//just for testing purposes
		requestContext.abortWith(Response.status(Response.Status.OK)
				.entity(
				utile.UniformementRepresentable.toString(new StringBuilder(),"A filter has intercepted this request"))
				.build());*/
		
	}

	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		responseContext.getHeaders().add("Header modified","with ContainerResponseFilter");
		
		
	}
}