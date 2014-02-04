package filter.server;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import serverLifter.archi.*;
import serverLifter.archi.LifterCaller;
import serverLifter.archi.ServerLifterCaller;

@Provider
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
		if(besoinFilter(input, classes)){
			LifterCaller lifterCaller = new ServerLifterCaller(input, classes);
			InputStream output = lifterCaller.callStream();
			requestContext.setEntityStream(output);
		}	
		System.out.println("Fin Corps du message :");
	}
	private boolean besoinFilter(InputStream input, Class<?>[] classes){
		boolean aRetourner=false;
		if(classes!=null && classes.length!=0){
			for(int i=0, l=classes.length; i<l; i++){
				if(!input.getClass().equals(classes[i].getClass())){
					if(classes[i].getClass().isInstance(input)){
						aRetourner=true;
						System.out.println("renvoie true...filtre executé");
					}	
				}
			}
		}
		return aRetourner;
		//classes.length!=0 && !input.getClass().equals(classes[0])
	}
}
/*		Class<?> expectedClass = (Class<?>)context.getGenericType();
Class<?>[] classesParametre=info.getResourceMethod().getParameterTypes();
Class<?>[] classes={expectedClass};
if(classesParametre!=null && classesParametre.length==2){
	if(Collection.class.isAssignableFrom(classesParametre[0]) && classesParametre[1].equals(Class.class)){
		Class<?>[] classes2={expectedClass, classesParametre[1]};
		classes=classes2;
	}
	else{ 
		if(Collection.class.isAssignableFrom(classesParametre[1]) && classesParametre[0].equals(Class.class)){
			Class<?>[] classes2={expectedClass, classesParametre[0]};
			classes=classes2;
		}else{
			Class<?>[] classes2={expectedClass};
			classes=classes2;
		}
	}
	ClientLifterCaller lifterCaller = new ClientLifterCaller(context.getInputStream(),classes);
	context.setInputStream(lifterCaller.callStream());
}
else{
	ClientLifterCaller lifterCaller = new ClientLifterCaller(context.getInputStream(),classes);
	context.setInputStream(lifterCaller.callStream());
}
return context.proceed();
}*/