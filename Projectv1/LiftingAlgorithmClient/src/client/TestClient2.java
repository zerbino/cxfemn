package client;

import java.util.LinkedList;
import java.util.List;

import model.Personne;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import com.sun.org.apache.bcel.internal.generic.NEW;

import filter.ResponseFilter;
import utile.UniformementRepresentable;

/**
 * Client utilisé pour faire le cas d'un filtre côté client. 
 * @author raphael
 *
 */
public class TestClient2 {

	public static void main(String[] args) {
		List<Object> filtres = new LinkedList<>();
		filtres.add(new ResponseFilter());
		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class, filtres);
		Personne etudiant = service.op();
		System.out.println(etudiant.getClass().getSimpleName());
		System.out.println(UniformementRepresentable.toString(
				new StringBuilder(), etudiant));
	}

}
