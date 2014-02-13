package client;

import interceptor.LiftingInterceptor;

import java.util.LinkedList;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import utile.UniformementRepresentable;
import example.model.Person;
import example.model.PersonImpl;
import example.model.StudentImpl;
import example.model.StudentWithoutFieldImpl;
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
		Person sbis = service.get_Person(sId);
		
		/*sId=service.post_Person(s);
		Person stierce = service.get_Person(sId);*/
		
		StudentWithoutFieldImpl s2=new StudentWithoutFieldImpl();
		s2.setFirstName("Martine");
		s2.setLastName("Monique");
		sId=service.post_PersonWithoutFieldImpl(s2);
		Person s2bis = service.get_Person(sId);
	}
}
