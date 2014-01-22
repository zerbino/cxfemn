package serverLifter.archi;


import org.jdom2.Document;

public class ServerLifting extends AbstractLifting<Class<?>[]> {

	public ServerLifting(Document doc, Class<?>[] clazz) {
		super(doc, clazz);
	}

	@Override
	public Document lifting() {
		
		Class<?> oneClass = this.clazz[0];
		this.indivLifting(doc.getRootElement(), oneClass);
		return this.doc;
		
	}

}
