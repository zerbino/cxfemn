package serverLifter.archi;


import java.lang.reflect.Type;

import org.jdom2.Document;

import adapters.InterfaceToClass;


/**
 * Extends AbstractLifting. Specifies the generic type with Class<?>[].
 * Implements the lifting() method, which calls the lifting algorithm, considering
 * that the operation performed is a request to the server.
 * 
 * @author raphael
 *
 */

public class ServerLifting extends AbstractLifting<Type> {

	public ServerLifting(Document doc, Type type, InterfaceToClass adpt) {
		super(doc, type, adpt);
	}

	@Override
	public Document lifting() {
		

		this.indivLifting(doc.getRootElement(), type);
		return this.doc;
		
	}

}
