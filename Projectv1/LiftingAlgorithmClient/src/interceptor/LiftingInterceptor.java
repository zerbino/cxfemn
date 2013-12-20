package interceptor;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import lifting.ClientLifterCaller;


@Provider
public class LiftingInterceptor implements ReaderInterceptor{

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context)
			throws IOException, WebApplicationException {
		Class<?> expectedClass = (Class<?>)context.getGenericType();
		ClientLifterCaller lifterCaller = new ClientLifterCaller(context.getInputStream(),expectedClass);
		context.setInputStream(lifterCaller.call());
		return context.proceed();
	}



}
