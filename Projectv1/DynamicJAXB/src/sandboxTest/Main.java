package sandboxTest;

import generated.Etudiant;
import generated.Personne;






import java.io.FileInputStream;
import java.io.InputStream;







import javax.xml.bind.Marshaller;

import org.eclipse.persistence.dynamic.DynamicEntity;
import org.eclipse.persistence.jaxb.JAXBMarshaller;

import djaxb.Djaxb;

public class Main{
	public static void main(String[] args) throws Exception {
		Djaxb djaxb = new Djaxb("documents/model.xsd");
		JAXBMarshaller m = djaxb.getMarshaller();
		Etudiant e = new Etudiant();
		e.setNom("Hao");
		e.setPrenom("ZHANG");
		e.setPromo("GSI");
		
		DynamicEntity etudiant = djaxb.getJAXBContext().newDynamicEntity("generated.Etudiant");
		etudiant.set("prenom", "Hao");
		etudiant.set("nom", "ZHANG");
		etudiant.set("promo", "GSI");
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(etudiant, System.out);
		//djaxb.getMarshaller().marshal(e, System.out);
		//InputStream xmlInputStream = new FileInputStream("documents/NinoLabruti.xml");
		//Object o = djaxb.getUnmarshaller().unmarshal(xmlInputStream);
		//JAXBElement<Etudiant> root = djaxb.getUnmarshaller().unmarshal(new StreamSource(new File("documents/NinoLabruti.xml")), Etudiant.class);
		//System.out.println(root.getClass());
		//DynamicEntity p = (DynamicEntity) JAXBIntrospector.getValue(djaxb.getUnmarshaller().unmarshal(xmlInputStream));
		 
		//String nom = p.get("nom");
		//String prenom = p.get("prenom");
		
		/*
		Object o = djaxb.getUnmarshaller().unmarshal(new File("documents/NinoLabruti.xml"));
		Class c = o.getClass();
		Field[] fs = c.getDeclaredFields();
		System.out.println(fs.length);
		for (Field f: fs){
			f.setAccessible(true);
			System.out.println(f);
		}
		*/
	}
}
