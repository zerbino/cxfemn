package serverLifter.archi;

import java.io.InputStream;

public class ServerLifterCaller extends AbstractLifterCaller<Class<?>[]> {

	public ServerLifterCaller(InputStream entity, Class<?>[] clazz) {
		super(entity, clazz);
		this.lifting = new ServerLifting(doc, clazz);
	}
	
	

}
