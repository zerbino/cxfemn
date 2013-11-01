package client;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import rest.Personne;
import rest.Service;

public class Client {
	public static void main(String args[]) {
		Service serv = JAXRSClientFactory.create(
				"http://localhost:8080/RESTValueSubstitution", Service.class);
		
		Personne substituedValue = new Personne();
		//Personne substituedValue = new Etudiant();
		substituedValue.setNom("Terieur");
		substituedValue.setPrenom("Alex");
		String _op__return = serv.op(substituedValue);
		System.out.println("op.result=" + _op__return);
		
	}
}
