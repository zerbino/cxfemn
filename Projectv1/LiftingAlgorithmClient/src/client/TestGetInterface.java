package client;

import interceptor.LiftingInterceptor;

import java.util.LinkedList;
import java.util.List;

import model.Personne;
import model.Etudiant;
import model.EtudiantImpl;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;


/**
 * Client utilisé pour tester les interfaces.
 * @author hao
 *
 */
public class TestGetInterface {
	
	public static void main(String[] args) {
		List<Object> filtres = new LinkedList<>();
		filtres.add(new LiftingInterceptor());
			Service service = JAXRSClientFactory.create(
					"http://localhost:8080/LiftingAlgorithm", 
					Service.class, filtres);
			Etudiant e = new EtudiantImpl();
			e.setId(1);
			e.setNom("ZHANG");
			e.setPrenom("Hao");
			e.setPromo("GSI");
			//String resu = service.opInt(e);
			//System.out.println(resu);
			
			Personne p = service.getPersonneInt();
			System.out.println(p.getNom() + " " + p.getPrenom());
	}
}
