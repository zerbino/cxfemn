package client;

import model.Etudiant;
import model.EtudiantImpl;
import model.Personne;
import model.Service;
import user.Client;
import utile.UniformementRepresentable;

/**
 * Test client to check the behavior of the solution client-side, i.e. when the
 * server is sending a response back to the client.
 * 
 * In this test : a service is called (class Service). The method op() is called
 * from that service, corresponding to a GET request. This method, on the
 * server, sends a Student, subtype of Person, while client-side a Person is
 * excepted.
 * 
 * @author hao
 * 
 */
public class TestClient5 extends Client<Service>{

	public TestClient5(String serverURI, Class<?> serverInterface) {
		super(serverURI, serverInterface);
	}

	public static void main(String[] args) {
		TestClient5 tc = new TestClient5("http://localhost:8080/LiftingAlgorithm", Service.class);
		Service service = tc.getService();
		EtudiantImpl e = new EtudiantImpl();
		e.setId(1);
		e.setNom("ZHANG");
		e.setPrenom("Hao");
		e.setPromo("GSI");
		String resu = service.op(e);
		System.out.println(resu);
	}

}
