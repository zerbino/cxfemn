package serverLifter.archi;

import java.io.InputStream;

import adapters.AdapterTackle;

public class ServerLifterCaller extends AbstractLifterCaller<Class<?>[]> {

	public ServerLifterCaller(InputStream entity, Class<?>[] clazz) {
		super(entity, clazz);
		this.lifting = new ServerLifting(doc, clazz, null);
	}

	public ServerLifterCaller(InputStream entity, Class<?>[] clazz,
			AdapterTackle adpt) {
		this(entity, clazz);
		this.adpt = adpt;
	}

}
