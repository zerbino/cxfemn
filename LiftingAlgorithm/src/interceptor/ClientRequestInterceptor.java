package interceptor;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import serverLifter.Lifter;

@Provider 
public class ClientRequestInterceptor implements ReaderInterceptor {
 
    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context)
                    throws IOException, WebApplicationException {
        InputStream originalInputStream = context.getInputStream();
        Lifter l=new Lifter();
        context.setInputStream(null);
        return context.proceed();
    }
}