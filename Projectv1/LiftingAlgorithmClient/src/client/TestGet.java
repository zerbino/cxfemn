package client;

import interceptor.LiftingInterceptor;

import java.util.LinkedList;
import java.util.List;

import model.PersonneImpl;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import tools.UniformementRepresentable;

/**
 * Test client to check the behavior of the solution client-side, i.e. when the
 * server is sending a response back to the client.
 * 
 * In this test : a service is called (class Service). The method op() is called
 * from that service, corresponding to a GET request. This method, on the
 * server, sends a Student, subtype of Person, while client-side a Person is
 * excepted.
 * 
 * @author raphael
 * 
 */
public class TestGet {

	public static void testWithFilter() {
		List<Object> filtres = new LinkedList<>();
		filtres.add(new LiftingInterceptor());
		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class,
				filtres);

		PersonneImpl etudiant = service.getPersonne();
		System.out.println(etudiant.getClass().getSimpleName());
		System.out.println(UniformementRepresentable.toString(
				new StringBuilder(), etudiant));
	}

	public static void testWithoutFilter() {
		List<Object> filtres = new LinkedList<>();
		filtres.add(new LiftingInterceptor());
		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class);

		PersonneImpl etudiant = service.getPersonne();
		System.out.println(etudiant.getClass().getSimpleName());
		System.out.println(UniformementRepresentable.toString(
				new StringBuilder(), etudiant));
	}

	public static void main(String[] args) {

		System.out.println("Test for a response to the client without filter:");

		try {
			testWithoutFilter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Test for a response to the client with the filter");
		testWithFilter();

	}

}
