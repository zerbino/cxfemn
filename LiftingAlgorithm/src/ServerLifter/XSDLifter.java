package ServerLifter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;

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
import org.xml.sax.SAXException;

import sun.tools.tree.ThisExpression;

public class XSDLifter {

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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private static String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }
         
        return null;
    }
	public int treeSize(Node n, int i){
		if(n.hasChildNodes()){
			i+=n.getChildNodes().getLength();
		}
		if(i>0){
			NodeList nodes = n.getChildNodes();
			for(int index=0;index<i;index++){
				i+=treeSize(nodes.item(index),0);
			}
		}
		return i;
	}
	protected void treeInclusion(Document received){
		//ajouter un algo de recherche de la classe adéquate
		Document expected=getDOMTree("documents/personne");
		if(treeSize(expected,0)!=treeSize(expected,0)){
			
			
			System.out.println(convertDocumentToString(received));
			received=expected;
		}
	}
	
	//the return String is the path to the doc output
	//normally lift should directly receive an xml file
	public static String Lift(Object o, String outputFile){
		//marshall(o,outputFile);
		Document doc = getDOMTree(outputFile);
		System.out.println(convertDocumentToString(doc));
		
		return null;
	}
}
