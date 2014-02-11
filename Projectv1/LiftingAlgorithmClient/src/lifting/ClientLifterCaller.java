package lifting;

import java.io.InputStream;
import java.lang.reflect.Type;

import org.jdom2.Document;

/**
 * Subclass of AbstractLifterCaller
 * 
 * @author raphael
 * 
 * @see AbstractLifterCaller for more details.
 *
 */
public class ClientLifterCaller extends AbstractLifterCaller<Type> {

	public ClientLifterCaller(InputStream entity, Type type) {
		super(entity, type);
		this.lifting = new ClientLifting(doc, type);
	}

	

}
