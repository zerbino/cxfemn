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
import annotations.AllowSubstitution;
import serverLifter.archi.LifterCaller;
import serverLifter.archi.ServerLifterCaller;

@Provider
@AllowSubstitution
@BindingPriority(BindingPriority.ENTITY_CODER)
public class ClientRequestFilter implements ContainerRequestFilter {
	@Context
	ResourceInfo info;

	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		System.out.println("Debut du filtre de la requête :");
		// Lifter lifter = new Lifter();
		// InputStream input =
		// lifter.HTTPAdapter(requestContext.getEntityStream(),info.getResourceMethod());
		InputStream input = requestContext.getEntityStream();
		Class<?>[] classes = info.getResourceMethod().getParameterTypes();
		if (classes.length > 0) {
			System.out.println(classes[0].getName());
			AdapterTackle adpt = new AdapterTackle(info.getResourceMethod()
					.getDeclaringClass().getPackage());
			LifterCaller lifterCaller = new ServerLifterCaller(input, classes,
					adpt);
			InputStream output = lifterCaller.callStream();
			requestContext.setEntityStream(output);
		}
		System.out.println("Fin du filtre de la requête :");
	}
}