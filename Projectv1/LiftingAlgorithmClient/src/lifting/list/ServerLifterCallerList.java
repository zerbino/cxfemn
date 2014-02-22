package lifting.list;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;

import lifting.AbstractLifterCaller;
import adapters.InterfaceToClass;

public class ServerLifterCallerList extends AbstractLifterCaller<ParameterizedType> {

	public ServerLifterCallerList(InputStream entity, ParameterizedType clazz, InterfaceToClass adpt) {
		super(entity, clazz, adpt);
		initLifting();
		
	}

	@Override
	protected void initLifting() {
		this.lifting=new ServerLiftingList(doc, clazz, adpt);
		
	}

}
