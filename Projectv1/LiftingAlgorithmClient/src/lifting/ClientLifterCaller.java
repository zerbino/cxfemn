package lifting;

import java.io.InputStream;
import java.lang.reflect.Type;

import org.jdom2.Document;

import adapters.InterfaceToClass;

/**
 * Subclass of AbstractLifterCaller
 * 
 * @author raphael
 * 
 * @see AbstractLifterCaller for more details.
 *
 */
public class ClientLifterCaller extends AbstractLifterCaller<Class<?>> {


	public ClientLifterCaller(InputStream entity, Class<?> type, InterfaceToClass adpt) {
		super(entity, type, adpt);
		initLifting();
	}

	@Override
	protected void initLifting() {
		this.lifting = new ClientLifting(doc, clazz, adpt);

	}

	

}
