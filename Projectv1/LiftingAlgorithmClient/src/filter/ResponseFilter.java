package filter;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.fusesource.hawtbuf.ByteArrayInputStream;

/**
 * 
 * @author raphael
 *
 * The filter called when a response is sent to the server. 
 */
@Provider
public class ResponseFilter implements ClientResponseFilter {
	
	@Context
	protected ResourceInfo info;

	@Override
	public void filter(ClientRequestContext arg0, ClientResponseContext response)
			throws IOException {
		System.out.println("coucou");
	}

}
