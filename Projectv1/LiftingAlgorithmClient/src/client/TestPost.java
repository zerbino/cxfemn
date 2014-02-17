package client;

import interceptor.LiftingInterceptor;
import interceptor.TestInterceptor;

import java.util.LinkedList;
import java.util.List;

import model.EtudiantImpl;
import model.Personne;
import model.PersonneImpl;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import utile.UniformementRepresentable;

public class TestPost {
	
	public static void main(String[] args) {

		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class);


		EtudiantImpl etudiant = new EtudiantImpl();
		etudiant.setPrenom("Kevin");
		etudiant.setNom("Llopart");
		etudiant.setPromo("GSI");
		System.out.println(service.postPersonne(etudiant));
	}
	
	

}
