package serverLifter;

//Créer une nouvelle class JDOM3
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;

import utile.UniformementRepresentable;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class JDom
{
	protected Document document;
	protected Element racine;
	protected String outputPath;

	public JDom(String filePath, String outputPath) throws Exception
	{
		this.outputPath=outputPath;
		SAXBuilder sxb = new SAXBuilder();
		document = sxb.build(new File(filePath));
		racine = document.getRootElement();
	}
	
	public Document Lift(){
		try {
			//TODO
			System.out.println("Before: ");
			print(racine);
			//we should get the method name in webinf
			treeInclusion("op","model.ServiceImpl");
			System.out.println("After: ");
			print(racine);
			SAXBuilder sxb = new SAXBuilder();
			return sxb.build(new File(outputPath));
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected void treeInclusion(String methodName,String className){
		LinkedList<Method> m=getMethod(methodName,className);
		//TODO
		//we should check which method the client is calling by comparing the number of
		//parameters, but right now I dont know how several parameters
		//are marshalled in a single XML file
		Method service = m.getFirst();

		//we assume that parameters are sorted
		//then we simply check the expected parameters match the received ones
		//TODO
		//implementation for a service with a single parameter
		rename(racine,service.getParameterTypes()[0].getSimpleName());
		removeExtraFields(racine,service.getParameterTypes()[0]);
	}

	public LinkedList<Method> getMethod(String methodName, String className){
		LinkedList<Method> retour=new LinkedList<Method>();
		try {
			Class<?> c=Class.forName(className);
			Method[] m=null;
			if (c!=null) m=c.getMethods();
			if(m!=null){
				for(int i=0;i<m.length;i++){
					if(m[i].getName().equals(methodName)){
						retour.add(m[i]);
					}
				}
				System.out.println(UniformementRepresentable.toString(new StringBuilder(),m));
			}
		} catch (SecurityException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return retour;
	}

	public void print(Element e) {
		List<Element> l =
				e.getChildren();
		Iterator<Element> i = l.iterator();
		while(i.hasNext())
		{
			Element courant = (Element)i.next();
			System.out.println(courant.getName());
			if(courant.getChildren().size()!=0) print(courant);
			else {if(courant.getValue()!=null){

				System.out.println("	"+courant.getValue());
			}}
		}
	}

	public void removeExtraFields(Element e, Class<?> class1){
		Field[] f = class1.getDeclaredFields();
		boolean b;
		List<Element> l = e.getChildren();
		Iterator<Element> i = l.iterator();
		while(i.hasNext())
		{
			Element courant=i.next();
			b=true;
			for(int j=f.length-1;j>=0;j--){
				if(f[j].getName().equals(courant.getName())){
					b=false;
				}
			}
			if(b){
				e.removeContent(courant);
			}
		}
		try {
			enregistrefichier();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void rename(Element e,String name) {
		e.setName(name);
	}

	public void enregistrefichier() throws Exception
	{
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		File f=new File(outputPath);
		if(!f.exists()) {
			f.getParentFile().mkdirs();
		}
		sortie.output(document, new FileOutputStream(outputPath));
	}

	public static void enregistrefichier(Element document,String outputPath) throws Exception
	{
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		File f=new File(outputPath);
		if(!f.exists()) {
			f.getParentFile().mkdirs();
		}
		sortie.output(document, new FileOutputStream(outputPath));
	}
}
