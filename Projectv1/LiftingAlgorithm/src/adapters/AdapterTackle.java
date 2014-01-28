package adapters;
/**
 * @author Hao ZHANG
 */
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

public class AdapterTackle {
	protected Map<Class<?>, Class<?>> adapters = new HashMap<>();
	
	public AdapterTackle(Package pkg){
		addFromPkg(adapters, pkg);
		System.out.println(adapters);
	}
	
	private void addFromPkg(Map<Class<?>, Class<?>> adps, Package pkg){
		Annotation[] aps = pkg.getAnnotations();
		for (Annotation ap: aps){
			if (ap instanceof XmlJavaTypeAdapters){
				XmlJavaTypeAdapters app = (XmlJavaTypeAdapters)ap;
				XmlJavaTypeAdapter[] ans = app.value();
				for (Annotation a: ans){
					if (a instanceof XmlJavaTypeAdapter){
						XmlJavaTypeAdapter ann = (XmlJavaTypeAdapter)a;
						Class<? extends XmlAdapter> cAdapter = ann.value();
						Type[] types = ((ParameterizedType)(cAdapter.getGenericSuperclass())).getActualTypeArguments();
						Class c = null;
						Class i = null;
						try {
							String typeAndName0 = types[0].toString();
							String typeAndName1 = types[1].toString();
							c = Class.forName(typeAndName0.split(" ")[1]);
							i = Class.forName(typeAndName1.split(" ")[1]);
							if (!adps.containsKey(i)){
								adps.put(i, c);
							}
						} catch (ClassNotFoundException e){
							e.printStackTrace();
						}
					}
				}
			}
		}
		
	}
	
	public Class<?> getClassByInterface(Class<?> intface){
		return adapters.get(intface);
	}
	
}
