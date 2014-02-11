package lifting;

import org.jdom2.Document;

import adapters.AdapterTackle;

/**
 * Subclass of AbstractLifting<E>. E is specified by Class<?>. 
 * 
 * @author raphael
 *
 * @ see AbstractLifting<E> for more details
 */
public class ClientLifting extends AbstractLifting<Class<?>> {

	public ClientLifting(Document doc, Class<?> clazz, AdapterTackle adpt) {
		super(doc, clazz, adpt);

	}

	@Override
	public Document lifting() {
		this.indivLifting(doc.getRootElement(), clazz);
		return doc;
	}

}
