package serverLifter.archi;


import java.lang.reflect.Type;

import org.jdom2.Document;

public class ServerLifting extends AbstractLifting<Type> {

	public ServerLifting(Document doc, Type type) {
		super(doc, type);
	}

	@Override
	public Document lifting() {
		this.indivLifting(doc.getRootElement(), this.type);
		return this.doc;
		
	}

}
