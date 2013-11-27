import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBElement;

import model.ObjectFactory;
import model.Personne;
import utile.UniformementRepresentable;

public class CoreRequirementTest5{

	public static void main(String[] args) {
		Test t=new Test("model","documents","CoreRequirement5-1.xml","CoreRequirement5-2.xml");
	
		ObjectFactory fabrique = new ObjectFactory();
		Personne A=t.personne("Carl", "Johnson");
		Personne B=t.personne("Carl", "Test");
		
		JAXBElement<Personne> Fa = fabrique.createPersonne(A);
		JAXBElement<Personne> Fb = fabrique.createPersonne(B);
		
		System.out.println("A: "+UniformementRepresentable.toString(new StringBuilder(), A));
		System.out.println("B: "+UniformementRepresentable.toString(new StringBuilder(),B));
		System.out.println("Fa: "+UniformementRepresentable.toString(new StringBuilder(), Fa.getValue()));
		System.out.println("Fb: "+UniformementRepresentable.toString(new StringBuilder(),Fb.getValue()));
		System.out.println("Are these objects (Fa and Fb) equal? "+UniformementRepresentable.equals(Fa,Fb));

	}
}
