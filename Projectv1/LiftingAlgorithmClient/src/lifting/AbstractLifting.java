package lifting;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

import adapters.InterfaceToClass;

/**
 * The abstract class containing the methods to lift a schema. It's extended by two classes :
 * one used to lift when the client is making a request to the server, the other when the
 * server is sending a response to the client. These 2 implementations are used by classes
 * extending from AbstractLifterCaller. 
 *  
 * @author Raphaël Martignoni
 *
 * @param <E> : a class, or a list of class. Corresponds to the arguments of a service
 * requested by a client to a server, or to the return type expected by a client in its
 * service proxy.
 */
public abstract class AbstractLifting<E> extends Lifting<E> {

	protected Document doc;
	protected E clazz;
	protected InterfaceToClass adpt;

	public AbstractLifting(Document doc, E clazz, InterfaceToClass adpt) {
		super();
		this.doc = doc;
		this.clazz = clazz;
		this.adpt = adpt;
	}

	/**
	 * Removes the fields from element which are not present in class1
	 * @return 
	 */
	
	@Override
	protected void removeExtraFields(Element e, Class<?> class1) {
		// dstClass the class used by marshalling, instead of the interface.
		Class<?> dstClass = class1;
		if (null != adpt){
			dstClass = adpt.getClassByInterface(class1);
			if (null == dstClass){
				dstClass = class1;
			}
		}
		List<Element> l = e.getChildren();
		Iterator<Element> i = l.iterator();
		while (i.hasNext()) {
			Element courant = i.next();

			Class<?> class2 = dstClass;
			while(class2 != null && !fieldIsInClass(courant, class2)) {
				/**Checks if the name of "courant" matches a field in the super-classes
				 * of dstClass.
				 */
				if(class2==Object.class){
					System.out.println("suppression"+courant.getName());
					i.remove();
					break;
				}
				class2=dstClass.getSuperclass();
			}
		}
	}
	/**Checks if the name of e matches a field in dstClass
	 */
	private final boolean fieldIsInClass(Element e, Class<?> dstClass) {
		System.out.println("classe de parametre de fieldisinclass :"+dstClass.getSimpleName());
		Field[] fields = dstClass.getDeclaredFields();
		for (int j = fields.length - 1; j >= 0; j--) {
			if (fields[j].getName().equals(e.getName())) {
				return true;
			}
		}
		return false;
	}


	/**
	 * This method is the heart of the lifting algorithm : it consists in
	 * comparing an xml element, representing a class schema, to the
	 * corresponding class. The aim is, if the xml schema represents a subclass
	 * of the class in argument, it casts the schema to the type of class1, by
	 * removing extra fields, and rename the root with the name of class1.
	 * 
	 * It calls to methods : removeExtraFields(@@@) and rename(@@@)
	 */
	@Override
	protected void indivLifting(Element element, Class<?> clazz) {
		System.out.println("classe attendue:"+clazz.getSimpleName());
		this.removeExtraFields(element, clazz);
		this.rename(element, clazz);
	}

	/**
	 * Renames the root element with the lower case name of the class
	 */
	@Override
	protected void rename(Element element, Class<?> clazz) {
		Class<?> dstClass = clazz;
		if (null != adpt){
			dstClass = adpt.getClassByInterface(clazz);
			
			if (null == dstClass){
				dstClass = clazz;
			}
		}
		element.setName(dstClass.getSimpleName().toLowerCase());
		System.out.println("After rename:" + element.getName());
	}
	


}
