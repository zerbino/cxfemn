package filter.server;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.ws.rs.BindingPriority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;


import adapters.InterfaceToClass;
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
		Type[] types = info.getResourceMethod().getParameterTypes();
		InterfaceToClass adpt;
		if (types.length > 0) {
			Type expectedType =types[0];
			if(expectedType instanceof ParameterizedType){
				ParameterizedType typeP=(ParameterizedType) expectedType;
				adpt = new InterfaceToClass(((Class<?>)typeP.getActualTypeArguments()[0]).getPackage());
			}
			else{
				if(true){
					adpt = new InterfaceToClass(((Class<?>)expectedType).getPackage());
				}
			}	
			LifterCaller lifterCaller = new ServerLifterCaller(input, expectedType,
					adpt);
			InputStream output = lifterCaller.callStream();
			requestContext.setEntityStream(output);
		}
		System.out.println("Fin du filtre de la requête :");
	}
}