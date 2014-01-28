package serverLifter.archi;


import org.jdom2.Document;

/**
 * Extends AbstractLifting. Specifies the generic type with Class<?>[].
 * Implements the lifting() method, which calls the lifting algorithm, considering
 * that the operation performed is a request to the server.
 * 
 * @author raphael
 *
 */
public class ServerLifting extends AbstractLifting<Class<?>[]> {

	public ServerLifting(Document doc, Class<?>[] clazz) {
		super(doc, clazz);
	}

	@Override
	public Document lifting() {
		
		Class<?> oneClass = this.clazz[0];
		this.indivLifting(doc.getRootElement(), oneClass);
		return this.doc;
		
	}

}
