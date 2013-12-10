package filter;

import java.io.IOException;
import java.io.InputStream;
<<<<<<< HEAD
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.ws.rs.BindingPriority;
import javax.ws.rs.Path;
=======

>>>>>>> c8e456753057c0c2c271decc4481d16781d211db
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
<<<<<<< HEAD
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Configurable;
import javax.ws.rs.core.UriInfo;
=======
>>>>>>> c8e456753057c0c2c271decc4481d16781d211db
import javax.ws.rs.ext.Provider;

import serverLifter.Lifter;

<<<<<<< HEAD
@Provider
//binding priority doesnt change the postmatching
//@BindingPriority(value = 999)
public class ClientRequestFilter implements ContainerRequestFilter,DynamicFeature {
	
	@Override
	//see http://www.snip2code.com/Embed/4747/A-DynamicFeature-implementation-for-bind
	//http://download.oracle.com/otn-pub/jcp/jaxrs-2_0-edr3-spec/jaxrs-2_0-edr3-spec.pdf?AuthParam=1386670885_0e6bc532650f88658f5fd71ba16c2284
	//section 6.5.3 page 42 aka 52 online
	public void configure(ResourceInfo resourceInfo, Configurable context) {
		Class<?> temp=resourceInfo.getResourceClass();
		Method m = resourceInfo.getResourceMethod();
		Annotation classPath=temp.getAnnotation(Path.class);
		Annotation methPath=m.getAnnotation(Path.class);
		String path="";
		while(temp!=null && (classPath == null || methPath == null)){
			System.out.println();
			System.out.println("first "+temp.getSimpleName());
			if(temp.getSuperclass()!=null && temp.getSuperclass()!=Object.class){
				temp=temp.getSuperclass();
			}
			else{
				Class<?>[] l = temp.getInterfaces();
				for(int i=0;i<l.length;i++){
					if(l[i].getAnnotation(Path.class)!=null){
						temp=l[i];
						break;
					}
				}
			}
			if(temp!=null){
				classPath=temp.getAnnotation(Path.class);
				try {
					m=temp.getMethod(m.getName(), m.getParameterTypes());
					if(methPath==null){
						methPath=m.getAnnotation(Path.class);
					}
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(classPath !=null &&methPath!=null){
			System.out.println("CA, CA MARCHE:"+classPath.toString()+methPath.toString());
			myRegister(temp,m,methPath,context);
		}
	}
	public void myRegister(Class<?> c,Method m, Annotation meth,Configurable context){
		//je compte me servir des autres arguments pour stocker dans une map 
		//les matchings adéquats
		//le context register ne sert qu'à faire intervenir le filtre 
		//sur des services en particuliers. Ici comme aucune restriction
		//n'est faite, cela ne change rien: il sera utilisé partout
		context.register(ClientRequestFilter.class);
	}
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		UriInfo routingContext = requestContext.getUriInfo();
		System.out.println("CA NE MARCHE PAS: "+routingContext.getMatchedResources().toString());
		System.out.println(routingContext.getMatchedURIs().toString());
		System.out.println(routingContext.getRequestUriBuilder().toTemplate());
		System.out.println(routingContext.getPathSegments().toString());
		
		
		System.out.println("Corps du message :");
		Lifter lifter = new Lifter();
		InputStream input = lifter.HTTPAdapter(requestContext.getEntityStream());
=======
//@PreMatching
@Provider
public class ClientRequestFilter implements ContainerRequestFilter{

	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		/*
		 * Cette partie là permet d'intercepter à l'entrée de la requête //just
		 * for testing purposes
		 * requestContext.abortWith(Response.status(Response.Status.OK) .entity(
		 * utile.UniformementRepresentable.toString(new
		 * StringBuilder(),"A filter has intercepted this request")) .build());
		 */

		System.out.println("Corps du message :");
		//String truc = Tools.inputStreamToString(requestContext
			//	.getEntityStream());
		//System.out.println(truc);
		Lifter lifter = new Lifter();
		InputStream input = lifter.HTTPAdapter(requestContext.getEntityStream());
		
>>>>>>> c8e456753057c0c2c271decc4481d16781d211db
		requestContext.setEntityStream(input);

	}
}