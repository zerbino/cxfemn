package lifting;

import java.io.InputStream;
import java.lang.reflect.Method;

public class LifterCaller {
	/**
	 * serviceCalled, the method called on the server which is sending the response
	 * serviceProxy, the proxy service used client side to build the client
	 */
	protected Method serviceCalled, serviceProxy;
	
	/**
	 * The InputStream containing the entity of the response 
	 */
	protected InputStream entity;

	public LifterCaller(Method serviceCalled, Method serviceProxy,
			InputStream entity) {
		super();
		this.serviceCalled = serviceCalled;
		this.serviceProxy = serviceProxy;
		this.entity = entity;
	}
	
	/**
	 * Calls the lifter if the ressource expected by the client is not the same
	 * type as that sent by the server. 
	 * @return an InputStream corresponding to the lifted tree, or the same tree
	 * 
	 */
	public InputStream call(){
		if(!serviceCalled.getReturnType().equals(serviceProxy.getReturnType())){
			return new ClientLifting(entity, serviceProxy).lifting();
		}
		return entity;
	}

}
