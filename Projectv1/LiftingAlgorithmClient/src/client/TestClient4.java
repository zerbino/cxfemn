package client;


import model.Etudiant;
import model.EtudiantImpl;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;


/**
 * Client utilisé pour tester les interfaces.
 * @author hao
 *
 */
public class TestClient4 {
	
	public static void main(String[] args) {
			Service service = JAXRSClientFactory.create(
					"http://localhost:8080/LiftingAlgorithm", Service.class);
			Etudiant e = new EtudiantImpl();
			e.setId(1);
			e.setNom("ZHANG");
			e.setPrenom("Hao");
			e.setPromo("GSI");
			String resu = service.opInt(e);
			System.out.println(resu);
	}
}
