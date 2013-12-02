package ServerLifter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.parsers.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utile.UniformementRepresentable;

public class XSDLifter {
	/** Utilities**/
	public static String metaSchema;

	public static void marshall(
			Object o, String outputFile) {
		FileOutputStream out = null;
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema =null;
			out = new FileOutputStream(outputFile);
			schema = factory.newSchema(new File(metaSchema));
			JAXBContext jc = JAXBContext.newInstance("model");
			Marshaller m = jc.createMarshaller();
			m.setSchema(schema);
			//I noticed here you need to add tools.jar from the jdk to make the generation to jaxb classes possible
			m.marshal(o, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Document getDOMTree(String xmlFile){
		try {
			// création d'une fabrique de documents
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

			// création d'un constructeur de documents
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();

			// lecture du contenu d'un fichier XML avec DOM
			File xml = new File(xmlFile);

			return constructeur.parse(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private static String convertDocumentToString(Node node) {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			// below code to remove XML declaration
			// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(node), new StreamResult(writer));
			String output = writer.getBuffer().toString();
			return output;
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return null;
	}
	public static int treeSize(Node n, int i){
		if(n!=null && n.hasChildNodes()){
			i+=n.getChildNodes().getLength();
			if(i>0){
				NodeList nodes = n.getChildNodes();
				for(int index=0;index<i;index++){
					i+=treeSize(nodes.item(index),0);
				}
			}
		}
		return i;
	}
	/**Utilities**/
	/** Lifting **/
	protected void treeInclusion(Document received,String methodName,String className){
		LinkedList<Method> m=getMethod(methodName,className);
		Iterator<Method> it=m.iterator();
		Method service = null;
		//we find the right method that the user is calling.
		while(it.hasNext()){
			Method temp=it.next();
			if(temp.getParameterTypes().length==received.getChildNodes().getLength()){
				service=temp;
				break;
			}
		}
		if(service==null) return;
		//we assume that parameters are sorted
		//then we simply check the expected parameters match the received ones
		for(int i=0;i<received.getChildNodes().getLength();i++){
			if(!received.getChildNodes().item(i).getNodeName().equals(service.getParameterTypes()[i].getSimpleName())){
				try{
					//the following won't work if we don't have the full qualified name for the class we're looking for
					//unless we manage to get every class within the current project.
					//Class.forName(received.getChildNodes().item(i).getNodeName()).asSubclass(service.getParameterTypes()[i]);
					received.renameNode(received.getChildNodes().item(i), "", service.getParameterTypes()[i].getSimpleName());
					received=(Document) eraseExtraFields(received.getChildNodes().item(i),service.getParameterTypes()[i]);
					
				}
				catch(Exception e){

					return;
				}
			}
		}
		System.out.println(convertDocumentToString(received));
	}
	protected Node eraseExtraFields(Node received, Class<?> class1) {
		Field[] f = class1.getDeclaredFields();
		boolean b;
		for(int i=0;i<received.getChildNodes().getLength();i++){
			b=true;
			for(int j=0;j<f.length;j++){
				if(f[j].getName().equals(received.getChildNodes().item(i).getNodeName())){
					b=false;
				}
			}
			if(b){
				received.removeChild(received.getChildNodes().item(i));
			}
		}
		return received;
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
	
	//normally lift should directly receive an xml file
	protected Document Lift(Document doc){
		//TODO
		System.out.println("Before: "+convertDocumentToString(doc));
		//we should get the method name in webinf
		treeInclusion(doc,"op","model.ServiceImpl");
		System.out.println("After: "+convertDocumentToString(doc));
		return doc;
	}
	//Add the HTTP request content as parameter
	public Document HTTPAdapter(){
		return Lift(getDOMTree("documents/NinoLabruti.xml"));
	}
	/** Lifting **/
}