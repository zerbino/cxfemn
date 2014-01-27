package user;

import interceptor.LiftingInterceptor;

import java.util.LinkedList;
import java.util.List;

import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

/**
 * 
 * @author Hao ZHANG
 *
 */
public abstract class Client<T extends Object> {
	protected List<Object> filtres = new LinkedList<>();
	protected T service;
	public Client(String serverURI, Class<?> serverInterface){
		filtres.add(new LiftingInterceptor());
		service = (T) JAXRSClientFactory.create(
				serverURI, serverInterface,
				filtres);
	}
	public T getService(){
		return service;
	}
}

