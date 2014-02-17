package client;

import interceptor.LiftingInterceptor;

import java.util.LinkedList;
import java.util.List;

import model.PersonneImpl;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import utile.UniformementRepresentable;

public class TestGetList {
	public static void main(String[] args) {

		List<Object> filtres = new LinkedList<>();
		filtres.add(new LiftingInterceptor());
		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class,
				filtres);


		
		List<PersonneImpl> l=service.getPersonnes();
		for(int i=0,k=l.size(); i<k; i++){
			PersonneImpl courant=l.get(i);
			System.out.println(UniformementRepresentable.toString(new StringBuilder(), courant));
		}
	}
	

}
