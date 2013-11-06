import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBElement;

import model.ObjectFactory;
import model.Personne;
import utile.UniformementRepresentable;

public class CoreRequirementTest5 extends Test{

	protected static String SCHEMA = "model";
	protected static String REP = "documents";
	protected static String FICHIER1 = "CoreRequirement5-1.xml";
	protected static String FICHIER2 = "CoreRequirement5-2.xml";
	
	public static void main(String[] args) {
		ObjectFactory fabrique = new ObjectFactory();
		Personne A=personne("Carl", "Johnson");
		Personne B=personne("Carl", "Test");
		
		JAXBElement<Personne> Fa = fabrique.createPersonne(A);
		JAXBElement<Personne> Fb = fabrique.createPersonne(B);
		
		System.out.println("A: "+UniformementRepresentable.toString(new StringBuilder(), A));
		System.out.println("B: "+UniformementRepresentable.toString(new StringBuilder(),B));
		System.out.println("Fa: "+UniformementRepresentable.toString(new StringBuilder(), Fa.getValue()));
		System.out.println("Fb: "+UniformementRepresentable.toString(new StringBuilder(),Fb.getValue()));
		System.out.println("Are these objects (Fa and Fb) equal? "+UniformementRepresentable.equals(Fa,Fb));

	}
}
