package lifting;

import java.lang.reflect.Type;

import org.jdom2.Document;

import adapters.InterfaceToClass;

/**
 * Subclass of AbstractLifting<E>. E is specified by Class<?>. 
 * 
 * @author raphael
 *
 * @ see AbstractLifting<E> for more details
 */
public class ClientLifting extends AbstractLifting<Type> {

	public ClientLifting(Document doc, Type type, InterfaceToClass adpt) {
		super(doc, type, adpt);

	}

	@Override
	public Document lifting() {
		this.indivLifting(doc.getRootElement(), type);
		return doc;
	}

}
