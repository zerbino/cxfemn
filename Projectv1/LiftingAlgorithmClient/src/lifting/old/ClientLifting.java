package lifting.old;

import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Class to use when the tree inclusion algorithm is used for a response to the
 * client. The class is only used for the lifting algorithm itself. The
 * conditions to call it must be coded elsewhere.
 * 
 * The treeInclusion method corresponds to what must be done depending on where
 * the entity is being sent (to the client or to the server).
 * 
 * @author raphael
 * 
 */
public class ClientLifting extends Lifting {
	
	private Class<?> proxyExpectedResource;

	public ClientLifting(InputStream ressource, Class<?> proxyExpectedResource) {
		super(ressource, null);
		this.proxyExpectedResource=proxyExpectedResource;
	}

	@Override
	protected void treeInclusion() {

		this.rename(racine, proxyExpectedResource.getSimpleName()
				.toLowerCase());
		this.removeExtraFields(racine, this.proxyExpectedResource);
	}

}
