package lifting;

import java.lang.reflect.Type;

import org.jdom2.Document;
import org.jdom2.Element;

public abstract class Lifting<E> {

	protected abstract boolean removeExtraFieldsFromList(Element e, Class<?> class1);
	
	protected abstract boolean removeExtraFields(Element e, Class<?> class1);
	
	protected abstract void indivLifting(Element element, Type clazz);
	
	protected abstract void renames(Element element, Class<?> clazz);
	
	protected abstract void rename(Element element, Class<?> clazz);
	
	public abstract Document lifting();
	
}
