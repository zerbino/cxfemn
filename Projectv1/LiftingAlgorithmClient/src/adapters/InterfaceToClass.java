package adapters;

/**
 * This class is used to detect the adapters between interfaces and classes and save
 * in a HashMap. When we do the lifting, we can find which class has implemented the
 * interface with the help of the HashMap.
 * 
 * 
 * @author Hao ZHANG
 *
 * @param Package
 */
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

public class InterfaceToClass {
	/**
	 * the hashmap for saving the relation between interfaces and classes. the
	 * key is the interface, the value is the class.
	 */
	protected Map<Class<?>, Class<?>> adapters = new HashMap<>();

	public InterfaceToClass(Package pkg) {
		addFromPkg(adapters, pkg);
		System.out.println(adapters);
	}

	/**
	 * Add the relations between interface and class in a package.
	 * 
	 * @param adps
	 *            the Map<Class<?>, Class<?>> for which be added in.
	 * @param pkg
	 *            the package for searching the annotations.
	 */
	private void addFromPkg(Map<Class<?>, Class<?>> adps, Package pkg) {
		// / get the annotations of the package, defined in the
		// package-info.java
		Annotation[] aps = pkg.getAnnotations();
		for (Annotation ap : aps) {
			if (ap instanceof XmlJavaTypeAdapters) {
				// / if it's a XmlJavaTypeAdapters, take out the elements.
				XmlJavaTypeAdapters app = (XmlJavaTypeAdapters) ap;
				XmlJavaTypeAdapter[] ans = app.value();
				for (XmlJavaTypeAdapter ann : ans) {
					Class<? extends XmlAdapter> cAdapter = ann.value();
					/// take out the types saved in the annotation.
					Type[] types = ((ParameterizedType) (cAdapter
							.getGenericSuperclass())).getActualTypeArguments();
					Class c = null;
					Class i = null;
					try {
						String typeAndName0 = types[0].toString();
						String typeAndName1 = types[1].toString();
						/// create the class from the name.
						c = Class.forName(typeAndName0.split(" ")[1]);
						i = Class.forName(typeAndName1.split(" ")[1]);
						/// add into the hashmap.
						if (!adps.containsKey(i)) {
							adps.put(i, c);
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

				}
			}
		}

	}
	
	/**
	 * Take out the class from an interface.
	 * @param intface the interface.
	 * @return the class for the interface.
	 */
	public Class<?> getClassByInterface(Class<?> intface) {
		return adapters.get(intface);
	}

}
