package serverLifter.archi;

import java.io.InputStream;

import org.jdom2.Document;

import adapters.AdapterTackle;
import factories.Factory;
import factories.FactoryImp;

/**
 * This class is extended by two other classes. It contains an abstract method : call(), which
 * is implemented in subclasses, and which specifies which implementation of AbstractLifting
 * must be called. 
 * 
 * The subclasses of this class are called in the filter, which intercepts the entity
 * stream of the request to the server, or of the response to the client. 
 * 
 * @author raphael
 *
 * @param <E>
 */
public abstract class AbstractLifterCaller<E> implements LifterCaller {
	
	/**
	 * The document to be compared with what's expected : 
	 * - For a request to the server : represents the argument(s) of the service
	 * - For a response to the client : represents the return type of the proxy service
	 */
	protected Document doc;
	protected E clazz;
	protected Lifting<E> lifting;
	protected AdapterTackle adpt = null;
	
	protected Factory factory = new FactoryImp();
	
	protected AbstractLifterCaller(InputStream entity, E clazz){
		this.doc = factory.createConverter().stream2Doc(entity);
		System.out.println("After stream2doc rootelement="+this.doc.getRootElement());
		this.clazz = clazz;
	}
	
	protected AbstractLifterCaller(InputStream entity, E clazz, AdapterTackle adpt){
		this(entity, clazz);
		this.adpt = adpt;
	}

	@Override
	public Document call() {
		return lifting.lifting();
	}
	
	@Override
	public InputStream callStream(){
		return factory.createConverter().doc2Stream(call());
	}
	
	

}
