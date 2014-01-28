package serverLifter.server;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;

import adapters.AdapterTackle;
import utile.UniformementRepresentable;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class JDom
{
	protected Document document;
	protected Element racine;
	protected String outputPath;
	protected Method service;
	protected AdapterTackle adpt;

	public JDom(String filePath, String outputPath, Method m) throws Exception
	{
		this.outputPath=outputPath;
		SAXBuilder sxb = new SAXBuilder();
		document = sxb.build(new File(filePath));
		racine = document.getRootElement();
		service=m;
		adpt = new AdapterTackle(service.getDeclaringClass().getPackage());
	}

	public Document Lift(){
		try {
			System.out.println("Before: ");
			print(racine);
			treeInclusion();
			System.out.println("After: ");
			print(racine);
			SAXBuilder sxb = new SAXBuilder();
			return sxb.build(new File(outputPath));
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected void treeInclusion(){
		Class<?> classDest = service.getParameterTypes()[0];
//-check annotation
		if (null != adpt.getClassByInterface(classDest)){
			classDest = adpt.getClassByInterface(classDest);
			System.out.println("cast to "+classDest.getName());
		}
//-----------------
		if(removeExtraFields(racine, classDest)){
			rename(racine,service.getParameterTypes()[0].getSimpleName().toLowerCase());
		}
	}

	public void print(Element e) {
		List<Element> l =
				e.getChildren();
		Iterator<Element> i = l.iterator();
		System.out.println(racine.getName());
		while(i.hasNext())
		{
			Element courant = (Element)i.next();
			System.out.print("	"+courant.getName());
			if(courant.getChildren().size()!=0) print(courant);
			else {if(courant.getValue()!=null){

				System.out.println(": "+courant.getValue());
			}}
		}
	}
	//remove fields that doesn't match any of those in the super class
	// and check that mandatory fields are present
	public boolean removeExtraFields(Element e, Class<?> class1){
		Field[] f = class1.getDeclaredFields();
		boolean b;
		List<Element> l = e.getChildren();
		Iterator<Element> i = l.iterator();
		int mandatoryFields=0;
		while(i.hasNext())
		{
			Element courant=i.next();
			b=true;
			for(int j=f.length-1;j>=0;j--){
				if(f[j].getName().equals(courant.getName())){
					b=false;
					mandatoryFields++;
					break;
				}
			}
			if(b){
				e.removeContent(courant);
			}
		}
		if(f.length==mandatoryFields){

			try {
				enregistrefichier();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return true;
		}
		else{
			return false;
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