import java.util.*;

import javax.xml.bind.*;
import javax.xml.bind.util.*;
import javax.xml.validation.*;

import utile.UniformementRepresentable;
import model.ObjectFactory;
import model.Personne;

import java.io.*;

public class CoreRequirementTest {

	protected static String SCHEMA = "model";
	protected static String REP = "documents";
	protected static String FICHIER1 = "CGFp.xml";
	protected static String FICHIER2 = "FCGp.xml";

	public static Personne personne(String firstname, String lastname) {
		Personne p = new Personne();
		p.setNom(lastname);
		p.setPrenom(firstname);
		return p;
	}

	public static void marshall(Object o, OutputStream out) {
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema = null;
			try {
				schema = factory
						.newSchema(new File(REP + "/" + SCHEMA + ".xsd"));
			} catch (Exception e) {
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
			Schema schema = null;
			try {
				schema = factory
						.newSchema(new File(REP + "/" + SCHEMA + ".xsd"));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			JAXBContext jc = JAXBContext.newInstance(SCHEMA);
			Unmarshaller u = jc.createUnmarshaller();
			u.setSchema(schema);
			doc = u.unmarshal(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	/**
	 * We want to illustrate here the fact that "The command generation and the
	 * composition of the schema compilation and generation commutes". It means
	 * EITHER generating a SOAP command from an object, then generating a schema
	 * from it and getting the compilation of it back to an object instance; OR
	 * making the generation & compilation and then generating a SOAP command of
	 * this object WILL LEAD to the same result. (core requirement 4, in The
	 * Substitution Principle in an Object-Oriented Framework for Web Services:
	 * From Failure to Success, p8)
	 **/
	public static void main(String[] args) {
		ObjectFactory fabrique = new ObjectFactory();
		Personne p = personne("Carl", "Johnson");
		try {

			JAXBElement<Personne> enveloppe1 = fabrique.createPersonne(p);
			FileOutputStream out = new FileOutputStream(REP + "/" + FICHIER1);
			marshall(enveloppe1, out);
			FileInputStream in = new FileInputStream(REP + "/" + FICHIER1);
			Object CGFp = unmarshall(in);

			out = new FileOutputStream(REP + "/" + FICHIER2);
			marshall(p, out);
			in = new FileInputStream(REP + "/" + FICHIER2);
			Object FCGp = unmarshall(in);

			System.out.println("CGFp: "
					+ UniformementRepresentable.toString(new StringBuilder(),
							((JAXBElement<?>) (CGFp)).getValue()));
			System.out.println("FCGp: "
					+ UniformementRepresentable.toString(new StringBuilder(),
							((JAXBElement<?>) (FCGp)).getValue()));
			System.out.println("Are these objects equal? "
					+ UniformementRepresentable.equals(CGFp, FCGp));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}