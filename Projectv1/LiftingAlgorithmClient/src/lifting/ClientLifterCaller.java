package lifting;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Set;

import model.Personne;

import org.reflections.Reflections;

public class ClientLifterCaller {
	/**
	 * The path called by the client. Is used to find the corresponding method in the 
	 * proxy
	 */
	
	/**
	 * The InputStream containing the entity of the response 
	 */
	protected InputStream entity;
	protected String pathCalled;
	
/**
 * 
 * @param pathCalled
 * @param entity
 */

	public ClientLifterCaller(String pathCalled,
			InputStream entity) {
		super();
		this.entity = entity;
		this.pathCalled=pathCalled;
	}
	
	/**
	 * Calls the lifter if the ressource expected by the client is not the same
	 * type as that sent by the server. 
	 * @return an InputStream corresponding to the lifted tree, or the same tree
	 * 
	 */
	public InputStream call(){
		
		Method proxyMethod = this.getCalledMethod();
		InputStream modifiedResource = new ClientLifting(entity, proxyMethod).lifting();
		
		return modifiedResource;
	}
	
	/**
	 * Returns the method called in the proxy.
	 * @return
	 */
	public Method getCalledMethod(){
		try {
			return this.getCalledMethodExc();
		} catch (ClassNotFoundException e) {
			System.err.print("no such class");
		} 
		return null;
		
		
	}
	
	private Method getCalledMethodExc() throws ClassNotFoundException{
		Class<?> ressource = Class.forName("model.Service");
		Method method=null;
		for(Method methode :ressource.getMethods()){
			if(methode.getName().equals("op")&&methode.getReturnType().equals(Personne.class)){
				method = methode;
			}
		}
		return method;
	}

}
