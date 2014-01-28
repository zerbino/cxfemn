package serverLifter.archi;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

import adapters.AdapterTackle;

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
	protected AdapterTackle adpt;

	public AbstractLifting(Document doc, E clazz, AdapterTackle adpt) {
		super();
		this.doc = doc;
		this.clazz = clazz;
		this.adpt = adpt;
	}

	/**
	 * Removes the fields from element which are not present in class1
	 * 
	 * TODO : solve the problem mentionned in the report.
	 */
	
	@Override
	protected boolean removeExtraFields(Element e, Class<?> class1) {
		/// dstClass the class used by marshalling, instead of the interface.
		Class<?> dstClass = class1;
		if (null != adpt){
			dstClass = adpt.getClassByInterface(class1);
			if (null == dstClass){
				dstClass = class1;
			}
		}
		Field[] fields = dstClass.getDeclaredFields();
		boolean isNotInSuperClass;
		List<Element> l = e.getChildren();
		Iterator<Element> i = l.iterator();
		int mandatoryFields = 0;
		while (i.hasNext()) {
			Element courant = i.next();
			isNotInSuperClass = true;
			for (int j = fields.length - 1; j >= 0; j--) {
				if (fields[j].getName().equals(courant.getName())) {
					isNotInSuperClass = false;
					mandatoryFields++;
					break;
				}
			}
			if (isNotInSuperClass) {
				i.remove();
			}
		}

		
		// TODO : to be completed !

		if (fields.length == mandatoryFields) {

			return true;
		} else {
			return false;
		}
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
		System.out.println(element);
		this.removeExtraFields(element, clazz);
		this.rename(element, clazz);
		System.out.println(element);
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
