package serverLifter.client;

import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Class to use when the tree inclusion algorithm is used for a response to the client.
 * @author raphael
 *
 */
public class ClientLifting extends Lifting {

	public ClientLifting(InputStream ressource, Method m) throws Exception {
		super(ressource, m);
	}

	@Override
	protected void treeInclusion() {
		
		this.rename(racine, service.getReturnType().getSimpleName());
		this.removeExtraFields(racine, service.getReturnType());
	}

}
