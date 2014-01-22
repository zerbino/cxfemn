package client;

import model.Etudiant;
import model.Personne;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import utile.UniformementRepresentable;

public class TestClientKevin {
	
	public static void main(String[] args) {

		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class);
		Etudiant etudiant = new Etudiant();
		etudiant.setPrenom("Kevin");
		etudiant.setNom("Llopart");
		etudiant.setPromo("GSI");
		System.out.println(etudiant.getClass().getSimpleName());
		System.out.println(UniformementRepresentable.toString(
				new StringBuilder(), etudiant));
	}

}
