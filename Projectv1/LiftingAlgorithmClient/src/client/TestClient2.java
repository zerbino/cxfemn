package client;

import model.Personne;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import utile.UniformementRepresentable;

/**
 * Client utilisé pour faire le cas d'un filtre côté client. 
 * @author raphael
 *
 */
public class TestClient2 {

	public static void main(String[] args) {
		
		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class);
		Personne etudiant = service.op();
		System.out.println(UniformementRepresentable.toString(
				new StringBuilder(), etudiant));
	}

}
