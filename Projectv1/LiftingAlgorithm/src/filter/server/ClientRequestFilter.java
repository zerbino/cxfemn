package filter.server;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

import javax.ws.rs.BindingPriority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import serverLifter.archi.LifterCaller;
import serverLifter.archi.ServerLifterCaller;
import serverLifter.archi.list.ServerLifterCallerList;
import adapters.InterfaceToClass;
import annotations.AllowSubstitution;

@Provider
@AllowSubstitution
@BindingPriority(BindingPriority.ENTITY_CODER)
public class ClientRequestFilter implements ContainerRequestFilter {
	@Context
	ResourceInfo info;

	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		System.out.println("Debut du filtre de la requête :");
		
		InputStream input = requestContext.getEntityStream();
		Type[] classes = info.getResourceMethod().getGenericParameterTypes();
		
		if (classes.length > 0) {
			if(classes[0] instanceof Class<?>){
				System.out.println("Filter : treating General case");
			InterfaceToClass adpt = new InterfaceToClass(info.getResourceMethod()
					.getDeclaringClass().getPackage());
			
			Class<?>[] toClassTab = Tools.toClassTab(classes);
			LifterCaller lifterCaller = new ServerLifterCaller(input, toClassTab, adpt);
			InputStream output = lifterCaller.callStream();
			requestContext.setEntityStream(output);
			}
			else{
				if(classes[0] instanceof ParameterizedType){
					System.out.println("Filter : treating a List");
					ParameterizedType expectedType = (ParameterizedType) classes[0];
					Class<?> genericType = (Class<?>)(expectedType.getActualTypeArguments()[0]);
					InterfaceToClass adpt = new InterfaceToClass(genericType.getPackage());
					LifterCaller lifterCaller = new ServerLifterCallerList(input,expectedType,adpt);
					InputStream output=lifterCaller.callStream();
					requestContext.setEntityStream(output);
				}
			}
		}
		System.out.println("Fin du filtre de la requête :");
	}
}