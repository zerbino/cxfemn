package filter;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import serverLifter.Lifter;

@Provider
public class ClientRequestFilter implements ContainerRequestFilter{
	@Context
	ResourceInfo info;
	
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		System.out.println("Corps du message :");
		Lifter lifter = new Lifter();
		InputStream input = lifter.HTTPAdapter(requestContext.getEntityStream(),info.getResourceMethod());
		requestContext.setEntityStream(input);
	}
}