package filter.server;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.eclipse.jdt.internal.compiler.ast.InstanceOfExpression;

import factories.Factory;
import factories.FactoryImp;
import serverLifter.archi.*;
import sun.security.jca.GetInstance.Instance;

@Provider
public class ClientRequestFilter implements ContainerRequestFilter{
	
	@Context
	ResourceInfo info;
	
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		System.out.println("Debut filtre de la requête :");
		InputStream input = requestContext.getEntityStream();
		Type[] typesRecu=info.getResourceMethod().getGenericParameterTypes();
		if(besoinFilter(input, typesRecu)){
			LifterCaller lifterCaller = new ServerLifterCaller(input, typesRecu[0]);
			InputStream output = lifterCaller.callStream();
			requestContext.setEntityStream(output);
		}	
		System.out.println("Fin filtre de la requête :");
	}
	
	private boolean besoinFilter(InputStream input, Type[] types){
		boolean aRetourner=false;
		if(types!=null && types.length!=0){
			for(int i=0, l=types.length; i<l; i++){
				Class<?> classesParametre;
				if(types[i] instanceof ParameterizedType){
					ParameterizedType expectedParameterizedType= (ParameterizedType) types[i];
					classesParametre= (Class<?>) expectedParameterizedType.getActualTypeArguments()[0];
				}	
				else{
					classesParametre= (Class<?>) types[i];
				}
				Factory factory = new FactoryImp();
				String classRecu = factory.createConverter().stream2Doc(input).getRootElement().getName();
				if(!classRecu.equals(classesParametre.getSimpleName())){
					aRetourner=true;
					System.out.println("renvoie true...filtre executé");	
				}
	
			}
		}
		return aRetourner;
	}
}
