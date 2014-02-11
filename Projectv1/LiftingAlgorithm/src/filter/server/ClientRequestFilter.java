package filter.server;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.BindingPriority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import adapters.AdapterTackle;
import serverLifter.archi.LifterCaller;
import serverLifter.archi.ServerLifterCaller;

@Provider
@AllowSubstitution
@BindingPriority(BindingPriority.ENTITY_CODER)
public class ClientRequestFilter implements ContainerRequestFilter{
	@Context
	ResourceInfo info;
	
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		System.out.println("Corps du message :");
		//Lifter lifter = new Lifter();
		//InputStream input = lifter.HTTPAdapter(requestContext.getEntityStream(),info.getResourceMethod());
		InputStream input = requestContext.getEntityStream();
		Class<?>[] classes=info.getResourceMethod().getParameterTypes();
		AdapterTackle adpt = new AdapterTackle(info.getResourceMethod().getDeclaringClass().getPackage());
		LifterCaller lifterCaller = new ServerLifterCaller(input, classes, adpt);
		InputStream output = lifterCaller.callStream();
		requestContext.setEntityStream(output);
	}
}