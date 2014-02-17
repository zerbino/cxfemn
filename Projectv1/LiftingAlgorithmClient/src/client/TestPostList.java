package client;

import interceptor.LiftingInterceptor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Etudiant;
import model.EtudiantImpl;
import model.PersonneImpl;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import utile.UniformementRepresentable;

public class TestPostList {
	public static void main(String[] args) {

		List<Object> filtres = new LinkedList<>();
		filtres.add(new LiftingInterceptor());
		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class);


		
		EtudiantImpl etudiant = new EtudiantImpl();
		etudiant.setNom("Bon");
		etudiant.setPrenom("Jean");
		etudiant.setPromo("gsi");
		EtudiantImpl etudiant1 = new EtudiantImpl();
		etudiant1.setNom("Bon1");
		etudiant1.setPrenom("Jean1");
		etudiant1.setPromo("gsi1");
		ArrayList<EtudiantImpl> listeEtudiant = new ArrayList<>();
		listeEtudiant.add(etudiant);
		listeEtudiant.add(etudiant1);
		
		service.postPersonnes(listeEtudiant);
		List<PersonneImpl> l=service.getPersonnesMemoire();
		for(int i=0,k=l.size(); i<k; i++){
			PersonneImpl courant=l.get(i);
			System.out.println(UniformementRepresentable.toString(new StringBuilder(), courant));
		}
	}
}
