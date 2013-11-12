import java.util.*;

import javax.xml.bind.*;
import javax.xml.bind.util.*;
import javax.xml.validation.*;

import utile.UniformementRepresentable;
import model.ObjectFactory;
import model.Personne;

import java.io.*;

public class CoreRequirementTest4{

	/** We want to illustrate here the fact that "The command
	generation and the composition of the schema compilation
	and generation commutes". 
	It means 
	EITHER generating a SOAP command from an object,
	then  generating a schema from it and getting the compilation of it back to an object instance;
	OR making the generation & compilation and then generating a SOAP command of this object
	WILL LEAD to the same result. (core requirement 4,  in 
	The Substitution Principle in an Object-Oriented
	Framework for Web Services: From Failure to Success, p8)
	
	
	**/
	public static void main(String[] args) {
		Test t=new Test("model","documents","CGFp.xml","FCGp.xml");
		ObjectFactory fabrique = new ObjectFactory();
		Personne p = t.personne("Carl", "Johnson");
		try {
			System.out.println(UniformementRepresentable.toString(new StringBuilder(), p));
			JAXBElement<Personne> enveloppe1 = fabrique.createPersonne(p);
			FileOutputStream out = new FileOutputStream(t.REP + "/" + t.FICHIER1);
			t.marshall(enveloppe1, out);
			FileInputStream in = new FileInputStream(t.REP + "/" + t.FICHIER1);
			Object CGFp = t.unmarshall(in);

			out = new FileOutputStream(t.REP + "/" + t.FICHIER2);
			t.marshall(p, out);
			in = new FileInputStream(t.REP + "/" + t.FICHIER2);
			Object FCGp = t.unmarshall(in);

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