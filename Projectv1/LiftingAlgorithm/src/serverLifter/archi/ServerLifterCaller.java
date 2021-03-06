package serverLifter.archi;

import java.io.InputStream;

import adapters.InterfaceToClass;

public class ServerLifterCaller extends AbstractLifterCaller<Class<?>[]> {

	public ServerLifterCaller(InputStream entity, Class<?>[] clazz, InterfaceToClass adpt) {
		super(entity, clazz, adpt);
		initLifting();
		
	}

	@Override
	protected void initLifting() {
		this.lifting = new ServerLifting(doc, clazz, adpt);
		
	}
}
