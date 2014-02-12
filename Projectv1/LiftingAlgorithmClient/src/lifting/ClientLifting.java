package lifting;

import java.lang.reflect.Type;

import org.jdom2.Document;

import adapters.AdapterTackle;

/**
 * Subclass of AbstractLifting<E>. E is specified by Class<?>. 
 * 
 * @author raphael
 *
 * @ see AbstractLifting<E> for more details
 */
public class ClientLifting extends AbstractLifting<Type> {

	public ClientLifting(Document doc, Type type, AdapterTackle adpt) {
		super(doc, type, adpt);

	}

	@Override
	public Document lifting() {
		//System.out.println("classe null?:"+clazz==null);
		//System.out.println("doc.getrrotelement is rootelement?:"+doc.getRootElement().isRootElement());
		this.indivLifting(doc.getRootElement(), type);
		return doc;
	}

}
