package client;

import interceptor.LiftingInterceptor;

import java.util.LinkedList;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import utile.UniformementRepresentable;
import example.model.Person;
import example.model.PersonImpl;
import example.model.StudentImpl;
import example.services.Service;

public class FullTest {

	public static void main(String[] args) {
		List<Object> filtres = new LinkedList<>();
		filtres.add(new LiftingInterceptor());
		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class);
		
		
		StudentImpl s=new StudentImpl();
		s.setFirstName("Kevin");
		s.setLastName("47");
		s.setSpecialField("hitman");
		int sId=service.post_PersonImpl(s);
		System.out.println(sId);
		
		Person sbis = service.get_Person(sId);
		//System.out.println(sbis.getClass().getSimpleName());
		//System.out.println(UniformementRepresentable.toString(
		//		new StringBuilder(), sbis));
	}
}
