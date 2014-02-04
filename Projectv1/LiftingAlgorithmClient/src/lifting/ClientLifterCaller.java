package lifting;

import java.io.InputStream;

import org.jdom2.Document;

/**
 * Subclass of AbstractLifterCaller
 * 
 * @author raphael
 * 
 * @see AbstractLifterCaller for more details.
 *
 */
public class ClientLifterCaller extends AbstractLifterCaller<Class<?>[]> {

	public ClientLifterCaller(InputStream entity, Class<?>[] clazz) {
		super(entity, clazz);
		this.lifting = new ClientLifting(doc, clazz);
	}

	

}
