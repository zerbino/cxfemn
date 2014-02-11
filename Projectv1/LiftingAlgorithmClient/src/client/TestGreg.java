package client;

import interceptor.LiftingInterceptor;
import interceptor.TestInterceptor;

import java.lang.reflect.Field;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import model.Etudiant;
import model.Personne;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;







import utile.UniformementRepresentable;

public class TestGreg {
	
	public static void main(String[] args) {
		
		List<Object> filtres = new LinkedList<>();
		filtres.add(new LiftingInterceptor());
		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class, filtres);
		/*Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class);*/
		Etudiant p1 = new Etudiant();
		p1.setId(0);
		p1.setNom("Séguin-Henry");
		p1.setPrenom("Grégoire");
		p1.setPromo("gsi");
		
		Etudiant p2 = new Etudiant();
		p2.setId(1);
		p2.setNom("Martignoni");
		p2.setPrenom("Raphaël");
		p2.setPromo("gsi");
	
		List<Etudiant> liste=new ArrayList();
		liste.add(p2);
		liste.add(p1);

		//TEST avec liste pour conversion list<Etudiant> à list<Personne> au niveau de la reponse
		List<Personne> listePersonne=service.opListe();
		if(listePersonne!=null)System.out.println("la liste est non null :"+listePersonne.size());
		for(int i=0;i<listePersonne.size();i++){
			System.out.println(listePersonne.get(i).getClass().getSimpleName());
			System.out.println(UniformementRepresentable.toString(
				new StringBuilder(), listePersonne.get(i)));
		}	
		System.out.println("commence le poste :"+service.op(liste));
		System.out.println(service.listePersonne());
		//TEST avec liste pour conversion list<Etudiant> à list<Personne> au niveau de la requete
		/*List<Etudiant> listeEtudiants= new ArrayList<Etudiant>();
		List<Personne> listePersonne=service.op(listeEtudiants);
		System.out.println(etudiant.getClass().getSimpleName());
		System.out.println(UniformementRepresentable.toString(
				new StringBuilder(), etudiant));*/
		

	}
}
