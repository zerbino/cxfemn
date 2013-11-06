import javax.xml.bind.JAXBElement;

import model.ObjectFactory;
import model.Personne;
import utile.UniformementRepresentable;


public class CoreRequirementTest6{
	public static void main(String[] args) {
		Test t=new Test("model","documents","CoreRequirement6.xml",null);
		ObjectFactory fabrique = new ObjectFactory();
		Personne A=t.personne("Carl", "Johnson");
		
		//createPersonne is our embedding function
		JAXBElement<Personne> Fa = fabrique.createPersonne(A);
		//getValue is our projection function
		Personne After=Fa.getValue();
		
		System.out.println("A: "+UniformementRepresentable.toString(new StringBuilder(), A));
		System.out.println("After (embedding and projection): "+UniformementRepresentable.toString(new StringBuilder(),After));
		System.out.println("Are these objects (A and After) equal? "+UniformementRepresentable.equals(A,After));

		JAXBElement<Personne> Fafter = fabrique.createPersonne(After);
		System.out.println("Fa: "+UniformementRepresentable.toString(new StringBuilder(), Fa.getValue()));
		System.out.println("Fafter (projection and embedding): "+UniformementRepresentable.toString(new StringBuilder(),Fafter.getValue()));
		System.out.println("Are these objects (Fa and Fafter) equal? "+UniformementRepresentable.equals(Fa,Fafter));

	}
}
