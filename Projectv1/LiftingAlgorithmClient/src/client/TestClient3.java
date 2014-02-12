package client;

import java.util.LinkedList;
import java.util.List;

import model.Personne;
import model.PersonneImpl;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;


/**
 * Client utilisé pour tester les services descriptionPersonne, modificationPersonne, et ajouterPersonne. 
 * @author grégoire
 *
 */
public class TestClient3 {
	//TestUselessPour le moment a modifier plus tard pour verifier la robustesse du programme
	public static void main(String[] args) {
			List<Object> filtres = new LinkedList<>();
			Service service = JAXRSClientFactory.create(
					"http://localhost:8080/LiftingAlgorithm", Service.class, filtres);
			PersonneImpl pa=new PersonneImpl();
			pa.setNom("Seguin-Henry");
			pa.setPrenom("Gregoire");
			pa.setId(45);
			PersonneImpl pb=new PersonneImpl();
			pb.setNom("Martignoni");
			pb.setPrenom("Raphaël");
			pb.setId(8);
			PersonneImpl pc=new PersonneImpl();
			pc.setNom("Llopart");
			pc.setPrenom("Kevin");
			pc.setId(77);
			PersonneImpl pd=new PersonneImpl();
			pd.setNom("Zhang");
			pd.setPrenom("Hao");
			pd.setId(56);						
			System.out.println(service.listePersonne());
			System.out.println(service.ajouterPersonne(pa));
			System.out.println(service.ajouterPersonne(pb));
			System.out.println(service.ajouterPersonne(pc));
			System.out.println(service.ajouterPersonne(pd));
			System.out.println(service.getPersonne(45));
			System.out.println(service.getPersonne(8));
			System.out.println(service.getPersonne(77));
			System.out.println(service.getPersonne(56));
			System.out.println(service.getPersonne(5));
			System.out.println(service.modificationNomPersonne(0, "Abracadabra"));
			System.out.println(service.modificationNomPersonne(77, "Abracadabra"));
			System.out.println(service.modificationPrenomPersonne(1, "Badaboum!"));
			System.out.println(service.modificationPrenomPersonne(56, "Badaboum!"));
			System.out.println(service.getPersonne(77));
			System.out.println(service.getPersonne(56));
			System.out.println(service.effacerPersonne(0));			
			System.out.println(service.effacerPersonne(8));		
			System.out.println(service.listePersonne());
			

			
			
	}
}
