package client;

import interceptor.LiftingInterceptor;
import interceptor.TestInterceptor;

import java.util.LinkedList;
import java.util.List;

import model.Personne;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

public class TestGreg {
	
	public static void main(String[] args) {

		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class);
		Personne p1 = new Personne();
		p1.setNom("Séguin-Henry");
		p1.setPrenom("Grégoire");
		
		Personne p2 = new Personne();
		p2.setNom("Martignoni");
		p2.setPrenom("Raphaël");
		
		
		service.op(p1);
		service.op(p2);
	}
	


}
