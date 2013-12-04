package client;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import serveur.Service;


public class Client {
	
	public static void main(String[] args) {
		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/EssaiFiltre",
				Service.class);
		System.out.println(service.getPersonne().getEntity().getClass());
	}

}
