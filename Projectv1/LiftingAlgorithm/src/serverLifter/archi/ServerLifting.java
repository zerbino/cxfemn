package serverLifter.archi;


import org.jdom2.Document;
import adapters.AdapterTackle;


/**
 * Extends AbstractLifting. Specifies the generic type with Class<?>[].
 * Implements the lifting() method, which calls the lifting algorithm, considering
 * that the operation performed is a request to the server.
 * 
 * @author raphael
 *
 */

public class ServerLifting extends AbstractLifting<Class<?>[]> {

	public ServerLifting(Document doc, Class<?>[] clazz, AdapterTackle adpt) {
		super(doc, clazz, adpt);
	}

	@Override
	public Document lifting() {
		
		if(this.clazz.length>0){
		Class<?> oneClass = this.clazz[0];
		this.indivLifting(doc.getRootElement(), oneClass);
		}
		return this.doc;
		
	}

}
