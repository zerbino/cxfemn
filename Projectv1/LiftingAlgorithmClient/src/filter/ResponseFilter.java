package filter;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.fusesource.hawtbuf.ByteArrayInputStream;

/**
 * 
 * @author raphael
 *
 * The filter called when a response is sent to the server. 
 */
@Provider
public class ResponseFilter implements ClientResponseFilter, ClientRequestFilter {
	
	@Context
	protected ResourceInfo info;
	@Context
	protected UriInfo uriInfo;

	@Override
	public void filter(ClientRequestContext arg0, ClientResponseContext response)
			throws IOException {
	}

	@Override
	public void filter(ClientRequestContext arg0) throws IOException {
		System.out.println(uriInfo.getAbsolutePath());
		System.out.println(uriInfo.getMatchedURIs());
	}

}
