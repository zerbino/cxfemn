package client;

import interceptor.LiftingInterceptor;

import java.util.LinkedList;
import java.util.List;



import model.*;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;


/**
 * Client utilisé pour tester les interfaces.
 * @author hao
 *
 */
public class Client {
	
	public static void main(String[] args) {
		List<Object> filtres = new LinkedList<>();
		filtres.add(new LiftingInterceptor());
			Service service = JAXRSClientFactory.create(
					"http://localhost:8080/DemoServer", 
					Service.class, filtres);
			Student e = new StudentImpl();
			e.setId(1);
			e.setLastname("ZHANG");
			e.setFirstname("Hao");
			e.setMajor("GSI");
			//String resu = service.opInt(e);
			//System.out.println(resu);
			
			Person p = service.opInt();
			System.out.println(p.getLastname() + " " + p.getFirstname());
	}
}
