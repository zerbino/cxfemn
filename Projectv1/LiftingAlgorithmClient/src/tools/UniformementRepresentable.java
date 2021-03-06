package tools;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UniformementRepresentable {

	private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

	public static String toString(StringBuilder sb, Object obj) {
		Class<? extends Object> cls = obj.getClass();
		Field[] f = cls.getDeclaredFields();
		for (int i = 0; i < f.length; i++) {
			try {
				f[i].setAccessible(true);
				String sep = ((i+1) < f.length)? "; " : ""; 
				if(f[i].get(obj)!=obj){
					if(f[i].get(obj)!=null&&!f[i].get(obj).getClass().isPrimitive()&&!isWrapperType(f[i].get(obj).getClass())){
						sb=new StringBuilder("[ "+f[i].getType().getSimpleName()+" " +f[i].getName()+toString(sb,f[i].get(obj))+" ]");
					}
					else {
						sb.append(f[i].getName() + " = " + f[i].get(obj) + " ");
					}
				} else {
					sb.append(f[i].getName() + " = this ");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb + "";
	}

	public static boolean equals(Object obj1, Object obj2) {
		Class<? extends Object> cls = obj1.getClass();
		Class<? extends Object> cls2 = obj2.getClass();
		if (obj1 == obj2)
			return true;
		if (cls != cls2)
			return false;
		// we can't get fields that come from a superclass
		Field[] f1 = getFields(cls);
		Field[] f2 = getFields(cls2);
		boolean ok;
		for (int i = 0; i < f1.length; i++) {
			ok = false;
			f1[i].setAccessible(true);
			for (int j = 0; j < f2.length; j++) {
				f2[j].setAccessible(true);
				try {
					if(f1[i].get(obj1)!=null&&f2[j].get(obj2)!=null&&f1[i].get(obj1)!=obj1&&f2[j].get(obj2)!=obj2){
						if(f1[i].getName().equals(f2[j].getName())){
							if(!f1[i].get(obj1).getClass().isPrimitive()&&!isWrapperType(f1[i].get(obj1).getClass())||!f2[j].get(obj2).getClass().isPrimitive()&&!isWrapperType(f2[j].get(obj2).getClass())){
								if(equals(f1[i].get(obj1),f2[j].get(obj2))){
									ok=true;
									break;
								}
							} else {
								if (f1[i].get(obj1).getClass().isPrimitive()
										&& f2[j].get(obj2).getClass()
												.isPrimitive()
										|| isWrapperType(f1[i].get(obj1)
												.getClass())
										&& isWrapperType(f2[j].get(obj2)
												.getClass())) {
									if (f1[i].get(obj1).equals(f2[j].get(obj2))) {
										ok = true;
									}
								}
								break;
							}
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			if (!ok)
				return false;
		}
		return true;
	}

	public static Field[] getFields(Class<?> type) {
		ArrayList<Field> ret = new ArrayList<Field>();
		ret = getAllFields(new ArrayList<Field>(), type);
		return ret.toArray(new Field[ret.size()]);
	}

	private static ArrayList<Field> getAllFields(ArrayList<Field> fields,
			Class<?> type) {
		for (Field field : type.getDeclaredFields()) {
			fields.add(field);
		}

		if (type.getSuperclass() != null) {
			fields = getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}

	public static boolean isWrapperType(Class<?> clazz) {
		return WRAPPER_TYPES.contains(clazz);
	}

	private static Set<Class<?>> getWrapperTypes() {
		Set<Class<?>> ret = new HashSet<Class<?>>();
		ret.add(Boolean.class);
		ret.add(Character.class);
		ret.add(Byte.class);
		ret.add(Short.class);
		ret.add(Integer.class);
		ret.add(Long.class);
		ret.add(Float.class);
		ret.add(Double.class);
		ret.add(Void.class);
		ret.add(String.class);
		ret.add(null);
		return ret;
	}
}