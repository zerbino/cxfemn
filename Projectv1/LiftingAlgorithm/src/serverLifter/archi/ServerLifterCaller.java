package serverLifter.archi;

import java.io.InputStream;
import java.lang.reflect.Type;

public class ServerLifterCaller extends AbstractLifterCaller<Type> {

	public ServerLifterCaller(InputStream entity, Type type) {
		super(entity, type);
		this.lifting = new ServerLifting(doc, type);
	}
	
	

}
