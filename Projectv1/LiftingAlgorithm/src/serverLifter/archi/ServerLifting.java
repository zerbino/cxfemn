package serverLifter.archi;


import org.jdom2.Document;

import adapters.AdapterTackle;

public class ServerLifting extends AbstractLifting<Class<?>[]> {

	public ServerLifting(Document doc, Class<?>[] clazz, AdapterTackle adpt) {
		super(doc, clazz, adpt);
	}

	@Override
	public Document lifting() {
		
		Class<?> oneClass = this.clazz[0];
		this.indivLifting(doc.getRootElement(), oneClass);
		return this.doc;
		
	}

}
