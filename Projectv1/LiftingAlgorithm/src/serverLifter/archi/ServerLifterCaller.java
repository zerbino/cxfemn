package serverLifter.archi;

import java.io.InputStream;
import java.lang.reflect.Type;

import adapters.InterfaceToClass;

public class ServerLifterCaller extends AbstractLifterCaller<Type> {

	public ServerLifterCaller(InputStream entity, Type type, InterfaceToClass adpt) {
		super(entity, type, adpt);
		this.lifting = new ServerLifting(doc, type, adpt);
		this.adpt = adpt;
	}
}
