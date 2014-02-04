package interceptor;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import lifting.ClientLifterCaller;

/**
 * This interceptor implements the JAX-RS ReaderInterceptor. It is added client-side and
 * is called when the client receives a response from the server. 
 * 
 * When this filter is called, it calls the lifting algorithm used client-side. The latter
 * compares the resource (the xml representing the entity, found in the context) with
 * what is expected in the client, which contains a proxy of the server. The expected resource
 * is given in argument of the client lifter caller (ClientLifterCaller), in this class. It
 * corresponds to the return type of the service called. 
 * 
 * @author Raphaël Martignoni
 *
 */




@Provider
public class LiftingInterceptor implements ReaderInterceptor{

	@Context
	ResourceInfo info;
	
	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context)
			throws IOException, WebApplicationException {
		System.out.println("debut intercpteur");
		Class<?> expectedClass = (Class<?>)context.getGenericType();
		Class<?>[] classes={expectedClass};
		if(info!=null && info.getResourceMethod()!=null){
			System.out.println("info non null et ses methode aussi");
			Class<?>[] classesParametre=info.getResourceMethod().getParameterTypes();
			if(classesParametre!=null && classesParametre.length==1 &&  
					Collection.class.isAssignableFrom(expectedClass)){
				System.out.println("filtrage de liste");
				Class<?>[] classes2={expectedClass, classesParametre[0]};
				classes=classes2;
			}
		}
		ClientLifterCaller lifterCaller = new ClientLifterCaller(context.getInputStream(),classes);
		context.setInputStream(lifterCaller.callStream());
		System.out.println("fin intercpteur");
		return context.proceed();
	}

}
