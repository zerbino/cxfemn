package model;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import tools.UniformementRepresentable;

//@InInterceptors(interceptors = {"interceptor.ClientRequestInterceptor"})
public class ServiceImpl implements Service {

	private ArrayList<PersonneImpl> listePersonneImpl=new ArrayList<>();
	private ArrayList<Personne> listePersonne = new ArrayList<>();
	
	
	public List<PersonneImpl> getPersonnesMemoire(){
		return listePersonneImpl;
	}
	public List<Personne> getPersonnesIntMemoire(){
		return listePersonne;
	}
	/////////////////////////////////  POST
	@Override
	public String opWithoutFields(PersonneWithoutFields p) {
		try {
			System.out.println("Objet reçu: "+UniformementRepresentable.toString(new StringBuilder(), p));			
			listePersonneImpl.add(p);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return UniformementRepresentable.toString(new StringBuilder(), p);
	}
	@Override
	public String postPersonne(PersonneImpl p) {
		try {
			System.out.println("Objet reçu: "+UniformementRepresentable.toString(new StringBuilder(), p));		
			listePersonneImpl.add(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UniformementRepresentable.toString(new StringBuilder(), p);
	}
	
	@Override
	public String postPersonneInt(Personne p) {
		PrintWriter writer;
		try {
			System.out.println("Objet reçu: "+UniformementRepresentable.toString(new StringBuilder(), p));
			listePersonne.add(p);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return UniformementRepresentable.toString(new StringBuilder(), p);
	}

	//////////////////////////////////////// GET
	@Override
	public EtudiantImpl getPersonne() {
		EtudiantImpl etudiant = new EtudiantImpl();
		etudiant.setNom("Bon");
		etudiant.setPrenom("Jean");
		etudiant.setPromo("gsi");
		return etudiant;
	}
	
	@Override
	public Etudiant getPersonneInt() {
		Etudiant etudiant = new EtudiantImpl();
		etudiant.setNom("Bon");
		etudiant.setPrenom("Jean");
		etudiant.setPromo("gsi");
		return etudiant;
	}
	
	///////////////////////////// GET LIST
	@Override
	public List<EtudiantImpl> getPersonnes() {
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
		return listeEtudiant;
	}

	@Override
	public List<Etudiant> getPersonnesInt() {
		Etudiant etudiant = new EtudiantImpl();
		etudiant.setNom("Bon");
		etudiant.setPrenom("Jean");
		etudiant.setPromo("gsi");
		Etudiant etudiant1 = new EtudiantImpl();
		etudiant1.setNom("Bon1");
		etudiant1.setPrenom("Jean1");
		etudiant1.setPromo("gsi1");
		ArrayList<Etudiant> listeEtudiant = new ArrayList<>();
		listeEtudiant.add(etudiant);
		listeEtudiant.add(etudiant1);
		return listeEtudiant;
	}
	////////////////////// POST LIST
	@Override
	public String postPersonnes(List<PersonneImpl> liste) {
		listePersonneImpl.addAll(liste);
		return "Ok";
	}

	@Override
	public String postPersonnesInt(List<Personne> liste) {
		listePersonne.addAll(liste);
		return "ok";
	}
	
	
	/*
	@Override
	public List<PersonneImpl> op(PersonneImpl p1, PersonneImpl p2) {
		listePersonneImpl.add(p2);
		listePersonneImpl.add(p1);
		return listePersonneImpl;
	}
	
	@Override
	public String getPersonne(int id) {
		int i=trouverPersonne(listePersonneImpl,id);
		if(i==-1){
			return "aucune personne ne possède cet identifiant:"+id;
		}
		return ""+UniformementRepresentable.toString(new StringBuilder(), listePersonneImpl.get(i));
	}
	
	@Override
	public String listePersonne() {
		int size=listePersonneImpl.size();
		if(size>0){
			String s="DEBUT LISTE:";
			for(int i=0;i<size;i++){
				s+="\n"+UniformementRepresentable.toString(new StringBuilder(), listePersonneImpl.get(i));
			}
			s+="\nEND LIST";
			return s;
		}
		else{
			return "liste vide";
		}
	}
	
	@Override
	public String modificationNomPersonne(int id, String nom) {
		int i=trouverPersonne(listePersonneImpl,id);
		if(i==-1){
			return "aucune personne ne possède cet identifiant:"+id;
		}
		listePersonneImpl.get(i).setNom(nom);
		return "modification effectué";
	}
	
	@Override
	public String modificationPrenomPersonne(int id, String prenom) {
		int i=trouverPersonne(listePersonneImpl,id);
		if(i==-1){
			return "aucune personne ne possède cet identifiant:"+id;
		}
		listePersonneImpl.get(i).setPrenom(prenom);
		return "modification effectué";
	}
	
	@Override
	public String ajouterPersonne(PersonneImpl p) {
		int i=trouverPersonne(listePersonneImpl,p.getId());
		if(i!=-1){
			return "Une personne possède déjà cet identifiant:"+p.getId();
		}
		listePersonneImpl.add(p);
		return "La personne suivante à été ajouté à la liste: \n"+UniformementRepresentable.toString(new StringBuilder(), p);
	}
	
	@Override
	public String effacerPersonne(int id) {
		int i=trouverPersonne(listePersonneImpl,id);
		if(i==-1){
			return "aucune personne ne possède cet identifiant:"+id;
		}
		listePersonneImpl.remove(i);
		return "La personne avec l'id:"+id+" a été supprimé";
	}	
	@Override
	public List<Personne> opInt(Personne p1, Personne p2) {
		listePersonne.add(p2);
		listePersonne.add(p1);
		return listePersonne;
	}

	@Override
	public String ajouterPersonneInt(Personne p) {
		int i=trouverPersonne(listePersonne,p.getId());
		if(i!=-1){
			return "Une personne possède déjà cet identifiant:"+p.getId();
		}
		listePersonne.add(p);
		return "La personne suivante à été ajouté à la liste: \n"+UniformementRepresentable.toString(new StringBuilder(), p);
	
	}
	*/


	private  <E extends Personne> int trouverPersonne  (List<E> liste,int id){
		int size=liste.size();
		if(size==0) return -1;
		int i=0;
		boolean a=true;
		do{
			if(liste.get(i).getId()==id){
				a=false;
			}
			else{
				i++;
			}
		}
		while(a && i<size);
		if(i>=size){
			return -1;
		}
		return i;
	}
}

