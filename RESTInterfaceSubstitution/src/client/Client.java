package client;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import rest.Personne;
import rest.Service;

public class Client {
	public static void main(String args[]) {
		ServiceE serv = JAXRSClientFactory.create(
				"http://localhost:8080/RESTInterfaceSubstitution", ServiceE.class);
		
		Etudiant substituedInterface = new Etudiant();
		substituedInterface.setNom("Terieur");
		substituedInterface.setPrenom("Alex");
		substituedInterface.setPrenom("A3");
		String _op__return = serv.op(substituedInterface);
		
		System.out.println("op.result=" + _op__return);
		
	}
}
