package serverLifter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.xml.parsers.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.jdom2.transform.JDOMSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import utile.UniformementRepresentable;

public class Lifter {
	//for sandbox tests
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
	//Add the HTTP request content as parameter
	public InputStream HTTPAdapter(String s){
		try {
			Random r=new Random();
			//FIXME
			//this is dirty. We should count the number of files in the folder and
			//name the file accordingly
			int randHash=r.nextInt()*100;

			String path=getClass().getClassLoader().getResource(".").getPath()+"FAKEDocuments/"+randHash+"Parameter.xml";
			String pathModified=getClass().getClassLoader().getResource(".").getPath()+"FAKEDocuments/"+randHash+"ParameterMODIFIED.xml";
			File f=new File(path);
			if(!f.exists()) {
				f.getParentFile().mkdirs();
			}
			Charset charset = Charset.forName("UTF-8");
			Writer writer = new OutputStreamWriter(new FileOutputStream(f), charset);
			try {
				writer.append(s);
			} finally {
				writer.close();
			}

			JDom j = new JDom(path,pathModified);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			Source xmlSource = new JDOMSource(j.Lift());
			Result outputTarget = new StreamResult(outputStream);
			TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
			return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}