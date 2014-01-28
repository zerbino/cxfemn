package model;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import utile.UniformementRepresentable;

//@InInterceptors(interceptors = {"interceptor.ClientRequestInterceptor"})
public class ServiceImpl implements Service {

	private ArrayList<PersonneImpl> listePersonneImpl=new ArrayList<>();
	private ArrayList<Personne> listePersonne = new ArrayList<>();
	
	
	@Override
	public String op(PersonneImpl p) {
		PrintWriter writer;
		try {
			//path correspond au répertoire d'installation de tomcat
			String path = getClass().getClassLoader().getResource(".").getPath()+"FAKEDocuments/FAKEdatabase.txt";
			File f = new File(path);
			if(!f.exists()) {
				f.getParentFile().mkdirs();
			}
			writer = new PrintWriter(path, "UTF-8");
			writer.append("Incoming request for: ");
			writer.append(UniformementRepresentable.toString(new StringBuilder(), p));
			writer.close();
			System.out.println("Objet reçu: "+UniformementRepresentable.toString(new StringBuilder(), p));
			System.out.println("Chemin vers la base de donnée: ");
			System.out.println(path);
			
			listePersonneImpl.add(p);
			System.out.println(p.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return p.getPrenom();
	}
	
	@Override
	public String opInt(Personne p) {
		PrintWriter writer;
		try {
			//path correspond au répertoire d'installation de tomcat
			String path = getClass().getClassLoader().getResource(".").getPath()+"FAKEDocuments/FAKEdatabase.txt";
			File f = new File(path);
			if(!f.exists()) {
				f.getParentFile().mkdirs();
			}
			writer = new PrintWriter(path, "UTF-8");
			writer.append("Incoming request for: ");
			writer.append(UniformementRepresentable.toString(new StringBuilder(), p));
			writer.close();
			System.out.println("Objet reçu: "+UniformementRepresentable.toString(new StringBuilder(), p));
			System.out.println("Chemin vers la base de donnée: ");
			System.out.println(path);
			
			listePersonne.add(p);
			System.out.println(p.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return p.getPrenom();
	}

	@Override
	public EtudiantImpl op() {
		EtudiantImpl etudiant = new EtudiantImpl();
		etudiant.setNom("Bon");
		etudiant.setPrenom("Jean");
		etudiant.setPromo("gsi");
		return etudiant;
	}
	
	@Override
	public Etudiant opInt() {
		Etudiant etudiant = new EtudiantImpl();
		etudiant.setNom("Bon");
		etudiant.setPrenom("Jean");
		etudiant.setPromo("gsi");
		return etudiant;
	}
	
	
	
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

	@Override
	public List<PersonneImpl> getPersonnes() {
		return this.listePersonneImpl;
	}

	@Override
	public List<Personne> opInt(Personne p1, Personne p2) {
		listePersonne.add(p2);
		listePersonne.add(p1);
		return listePersonne;
	}

	@Override
	public String ajouterPersonne(Personne p) {
		int i=trouverPersonne(listePersonne,p.getId());
		if(i!=-1){
			return "Une personne possède déjà cet identifiant:"+p.getId();
		}
		listePersonne.add(p);
		return "La personne suivante à été ajouté à la liste: \n"+UniformementRepresentable.toString(new StringBuilder(), p);
	
	}

	@Override
	public List<Personne> getPersonnesInt() {
		return this.listePersonne;
	}


}

