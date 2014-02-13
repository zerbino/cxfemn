package client;

import interceptor.LiftingInterceptor;

import java.util.LinkedList;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import utile.UniformementRepresentable;
import example.model.Person;
import example.services.Service;

public class FullTest {

	public static void main(String[] args) {
		List<Object> filtres = new LinkedList<>();
		filtres.add(new LiftingInterceptor());
		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class,
				filtres);
		Person etudiant = service.get_Person(0);
		System.out.println(etudiant.getClass().getSimpleName());
		System.out.println(UniformementRepresentable.toString(
				new StringBuilder(), etudiant));
	}

}
