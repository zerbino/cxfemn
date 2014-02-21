//package serverLifter.archi;
//
//import org.jdom2.Document;
//import org.jdom2.Element;
//
///**
// * The highest abstract class of the Lifting architecture. Declares the methods to be
// * implemented. The method lifting() is called in the implementation of LifterCaller.
// * 
// * @author raphael
// *
// * @param <E> The parameter of the expected resource. For a request to the server, it is
// * the list of arguments of the resource method. For a response to the client, it is the
// * return type of the proxy resource method. It can be an array of class.
// */
//public abstract class Lifting<E> {
//	
//	/**
//	 * Check if the names of sub-elements in Element e match fields in class1 or super-classes
//	 * of class1. If not, removes these sub-elements.
//	 */
//	protected abstract void removeExtraFields(Element e, Class<?> class1);
//	
//	/**
//	 * This method is the heart of the lifting algorithm : it consists in
//	 * comparing an xml element, representing a class schema, to the
//	 * corresponding class. The aim is, if the xml schema represents a subclass
//	 * of the class in argument, it casts the schema to the type of class1, by
//	 * removing extra fields, and rename the root with the name of class1.
//	 * 
//	 * It calls to methods : removeExtraFields(@@@) and rename(@@@)
//	 */
//	protected abstract void indivLifting(Element element, Class<?> clazz);
//	
//	/**
//	 * Renames the root element with the lower case name of the class
//	 */
//	protected abstract void rename(Element element, Class<?> clazz);
//	
//	/**
//	 * The only public method of the class. Performs the lifting by calling the other methods implemented
//	 * in the class (rename and indivLifting).
//	 * @return the lifted document
//	 */
//	public abstract Document lifting();
//	
//}
package serverLifter.archi;

import java.lang.reflect.Type;

import org.jdom2.Document;
import org.jdom2.Element;

/**
 * The highest abstract class of the Lifting architecture. Declares the methods to be
 * implemented. The method lifting() is called in the implementation of LifterCaller.
 * 
 * @author raphael
 *
 * @param <E> The parameter of the expected resource. For a request to the server, it is
 * the list of arguments of the resource method. For a response to the client, it is the
 * return type of the proxy resource method. It can be an array of class.
 */
public abstract class Lifting<E> {

	protected abstract void removeExtraFields(Element e, Class<?> class1);
	
	/**
	 * This method is the heart of the lifting algorithm : it consists in
	 * comparing an xml element, representing a class schema, to the
	 * corresponding class. The aim is, if the xml schema represents a subclass
	 * of the class in argument, it casts the schema to the type of class1, by
	 * removing extra fields, and rename the root with the name of class1.
	 * 
	 * It calls to methods : removeExtraFields(@@@) and rename(@@@)
	 */
	protected abstract void indivLifting(Element element, Class<?> clazz);
	
	/**
	 * Renames the root element with the lower case name of the class
	 */
	protected abstract void rename(Element element, Class<?> clazz);

	/**
	 * The only public method of the class. Performs the lifting by calling the other methods implemented
	 * in the class (rename and indivLifting).
	 * @return the lifted document
	 */
	public abstract Document lifting();
	





}
