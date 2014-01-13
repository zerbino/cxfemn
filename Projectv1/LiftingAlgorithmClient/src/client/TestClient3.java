package client;

import java.util.LinkedList;
import java.util.List;

import model.Etudiant;
import model.Personne;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import utile.UniformementRepresentable;
import filter.ResponseFilter;

/**
 * Client utilisé pour tester les services descriptionPersonne, modificationPersonne, et ajouterPersonne. 
 * @author grégoire
 *
 */
public class TestClient3 {
	
	public static void main(String[] args) {
			List<Object> filtres = new LinkedList<>();
			filtres.add(new ResponseFilter());
			Service service = JAXRSClientFactory.create(
					"http://localhost:8080/LiftingAlgorithm", Service.class, filtres);
			Personne pa=new Personne();
			pa.setNom("Seguin-Henry");
			pa.setPrenom("Gregoire");
			pa.setId(45);
			Personne pb=new Personne();
			pb.setNom("Martignoni");
			pb.setPrenom("Raphaël");
			pb.setId(8);
			Personne pc=new Personne();
			pc.setNom("Llopart");
			pc.setPrenom("Kevin");
			pc.setId(77);
			Personne pd=new Personne();
			pd.setNom("Zhang");
			pd.setPrenom("Hao");
			pd.setId(56);
			System.out.println(service.listePersonne());
			System.out.println(service.ajouterPersonne(pa));
			System.out.println(service.ajouterPersonne(pb));
			System.out.println(service.ajouterPersonne(pc));
			System.out.println(service.ajouterPersonne(pd));
			System.out.println(service.getPersonne(0));
			System.out.println(service.getPersonne(1));
			System.out.println(service.getPersonne(2));
			System.out.println(service.getPersonne(3));
			System.out.println(service.modificationNomPersonne(0, "Abracadabra"));
			System.out.println(service.modificationPrenomPersonne(1, "Badaboum!"));
			System.out.println(service.getPersonne(0));
			System.out.println(service.getPersonne(1));
			System.out.println(service.effacerPersonne(0));			
			System.out.println(service.listePersonne());
			
			
	}
}
