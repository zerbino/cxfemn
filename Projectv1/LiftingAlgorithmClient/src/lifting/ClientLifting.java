package lifting;

import org.jdom2.Document;

/**
 * Subclass of AbstractLifting<E>. E is specified by Class<?>. 
 * 
 * @author raphael
 *
 * @ see AbstractLifting<E> for more details
 */
public class ClientLifting extends AbstractLifting<Class<?>[]> {

	public ClientLifting(Document doc, Class<?>[] clazz) {
		super(doc, clazz);

	}

	@Override
	public Document lifting() {
		this.indivLifting(doc.getRootElement(), clazz);
		return doc;
	}

}
