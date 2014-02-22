package interceptor;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import tools.UniformementRepresentable;
import adapters.InterfaceToClass;
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

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context)
			throws IOException, WebApplicationException {
		System.out.println("Debut intercepteur de la réponse");
		Class<?> expectedType = (Class<?>)context.getGenericType();
		if (UniformementRepresentable.getWrapperTypes().contains(expectedType)){
			System.out.println("It's a general type, don't need lifting.");
		}
		else{
			InterfaceToClass adpt = new InterfaceToClass(((Class<?>)expectedType).getPackage());
			ClientLifterCaller lifterCaller = new ClientLifterCaller(context.getInputStream(),expectedType, adpt);
			context.setInputStream(lifterCaller.callStream());
		}
		System.out.println("Fin intercepteur de la réponse");
		return context.proceed();
	}

}
