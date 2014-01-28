package client;

import model.EtudiantImpl;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import utile.UniformementRepresentable;

public class TestClientKevin {
	
	public static void main(String[] args) {

		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class);
		EtudiantImpl etudiant = new EtudiantImpl();
		etudiant.setPrenom("Kevin");
		etudiant.setNom("Llopart");
		etudiant.setPromo("GSI");
		System.out.println(etudiant.getClass().getSimpleName());
		System.out.println(UniformementRepresentable.toString(
				new StringBuilder(), etudiant));
		String ret = service.op(etudiant);
		System.out.println("after lifting : "+ret);
	}

}
