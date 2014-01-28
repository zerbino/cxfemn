package client;

import interceptor.LiftingInterceptor;

import java.util.LinkedList;
import java.util.List;

import model.EtudiantWithoutFields;
import model.PersonneImpl;
import model.PersonneWithoutFields;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import utile.UniformementRepresentable;

/**
 * Test client to check the behavior of the solution client-side, i.e. when the
 * server is sending a response back to the client.
 * 
 * 
 * 
 */
public class TestClientWithoutFields {

	public static void main(String[] args) {
		List<Object> filtres = new LinkedList<>();
		filtres.add(new LiftingInterceptor());
		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class,
				filtres);
		EtudiantWithoutFields e=new EtudiantWithoutFields();
		e.setId(666);
		e.setNom("Jean");
		e.setPrenom("Aymare");
		System.out.println(service.opWithoutFields(e));
	}

}
