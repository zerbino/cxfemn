import java.util.*;

import javax.xml.bind.*;
import javax.xml.bind.util.*;
import javax.xml.validation.*;

import utile.UniformementRepresentable;
import model.ObjectFactory;
import model.Personne;

import java.io.*;

public class Test {

	protected static String SCHEMA;
	protected static String REP;
	protected static String FICHIER1;
	protected static String FICHIER2;

	public static Personne personne(String firstname,
			 String lastname) {
		Personne p = new Personne();
		p.setNom(lastname);
		p.setPrenom(firstname);
		return p;
	}

	public static void marshall(
			Object o, OutputStream out) {
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema =null;
			try {
				schema = factory.newSchema(new File(REP + "/" + SCHEMA
						+ ".xsd"));
			}
			catch(Exception e){
				e.printStackTrace();
				return;
			}
			JAXBContext jc = JAXBContext.newInstance(SCHEMA);
			Marshaller m = jc.createMarshaller();
			m.setSchema(schema);
			m.marshal(o, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object unmarshall(InputStream in) {
		Object doc = null;
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema =null;
			try {
				schema = factory.newSchema(new File(REP + "/" + SCHEMA
						+ ".xsd"));
			}
			catch(Exception e){
				e.printStackTrace();
				return null;
			}
			JAXBContext jc = JAXBContext.newInstance(SCHEMA);
			Unmarshaller u = jc.createUnmarshaller();
			u.setSchema(schema);
			doc =  u.unmarshal(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
}
