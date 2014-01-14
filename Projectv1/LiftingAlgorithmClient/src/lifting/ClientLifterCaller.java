package lifting;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Set;

import model.Personne;

import org.reflections.Reflections;

/**
 * ClientLifterCaller calls the ClientLifting class, which implements the lifting
 * algorithm used client side. The method call() returns the result of this algorithm
 * @author Raphaël Martignoni
 *
 *
 */
public class ClientLifterCaller {

	
	/**
	 * The InputStream containing the entity of the response 
	 */
	protected InputStream entity;
	
	protected Class<?> returnType;
	
/**
 * 
 * @param pathCalled
 * @param entity
 */

	public ClientLifterCaller(
			InputStream entity, Class<?> returnType) {
		super();
		this.entity = entity;
		this.returnType=returnType;
	}
	
	/**
	 * Calls the lifter if the ressource expected by the client is not the same
	 * type as that sent by the server. 
	 * @return an InputStream corresponding to the lifted tree, or the same tree
	 * 
	 */
	public InputStream call(){
		
		InputStream modifiedResource = new ClientLifting(entity, returnType).lifting();
		
		return modifiedResource;
	}
	



}
