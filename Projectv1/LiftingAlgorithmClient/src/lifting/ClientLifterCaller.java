package lifting;

import java.io.InputStream;

import org.jdom2.Document;

import adapters.AdapterTackle;

/**
 * Subclass of AbstractLifterCaller
 * 
 * @author raphael
 * 
 * @see AbstractLifterCaller for more details.
 *
 */
public class ClientLifterCaller extends AbstractLifterCaller<Class<?>> {

	public ClientLifterCaller(InputStream entity, Class<?> clazz, AdapterTackle adpt) {
		super(entity, clazz, adpt);
		this.lifting = new ClientLifting(doc, clazz, adpt);
	}

	

}
