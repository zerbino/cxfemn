package serverLifter.archi.list;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

import serverLifter.archi.AbstractLifting;
import adapters.InterfaceToClass;

public class ServerLiftingList extends AbstractLifting<ParameterizedType> {

	public ServerLiftingList(Document doc, ParameterizedType clazz, InterfaceToClass adpt) {
		super(doc, clazz, adpt);
	}
	
	/**
	 * Rename the document with the right name
	 */
	private void renameList(){
		Class<?> genericType = (Class<?>)clazz.getActualTypeArguments()[0];
		String newName = genericType.getSimpleName().toLowerCase()+"s";
		newName = newName.toLowerCase()+"s";
		System.out.println(doc.getRootElement());
		this.doc.getRootElement().setName(newName);
		System.out.println(this.doc.getRootElement());

	}
	
	
	@Override
	public Document lifting(){
		System.out.println("ServerLiftingList : "+clazz.getActualTypeArguments()[0]);
		System.out.println(doc.getRootElement().toString());
		this.renameList();
		Element rootElement = doc.getRootElement();
		List<Element> elements = rootElement.getChildren();
		for(Element element:elements){
			this.indivLifting(element, (Class<?>)(clazz.getActualTypeArguments()[0]));

		}
		return doc;
	}

	
}
