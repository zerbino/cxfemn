import java.util.*;

import javax.xml.bind.*;
import javax.xml.bind.util.*;
import javax.xml.validation.*;

import utile.UniformementRepresentable;
import model.ObjectFactory;
import model.Personne;

import java.io.*;

public class Test {

	protected String SCHEMA;
	protected String REP;
	protected String FICHIER1;
	protected String FICHIER2;
	
	public Test(String sCHEMA, String rEP, String fICHIER1,
			String fICHIER2) {
		super();
		SCHEMA = sCHEMA;
		REP = rEP;
		FICHIER1 = fICHIER1;
		FICHIER2 = fICHIER2;
	}

	public Personne personne(String firstname,
			 String lastname) {
		Personne p = new Personne();
		p.setNom(lastname);
		p.setPrenom(firstname);
		return p;
	}

	public void marshall(
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

	public Object unmarshall(InputStream in) {
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
