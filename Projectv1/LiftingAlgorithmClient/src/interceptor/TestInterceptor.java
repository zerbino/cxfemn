package interceptor;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import lifting.conversion.StreamConversionImp;

import org.jdom2.Document;

import test.Variables;

public class TestInterceptor implements ReaderInterceptor{

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context)
			throws IOException, WebApplicationException {
		InputStream input = context.getInputStream();
		StreamConversionImp converter = new StreamConversionImp();
		Document document = converter.stream2Doc(input);
		Variables.printDoc(document);
		return context.proceed();
	}

}
