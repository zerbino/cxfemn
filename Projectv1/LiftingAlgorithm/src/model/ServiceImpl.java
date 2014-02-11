package model;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import utile.UniformementRepresentable;

//@InInterceptors(interceptors = {"interceptor.ClientRequestInterceptor"})
public class ServiceImpl implements Service {

	private ArrayList<Personne> listePersonne=new ArrayList<Personne>();
	
	@Override
	public String op(Personne p) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return UniformementRepresentable.toString(new StringBuilder(), p);
	}

	@Override
	public Etudiant op() {
		Etudiant etudiant = new Etudiant();
		etudiant.setNom("Bon");
		etudiant.setPrenom("Jean");
		etudiant.setPromo("gsi");
		return etudiant;
	}
	
	@Override
	public String op(List<Personne> liste) {
		listePersonne.addAll(liste);
		return "ok";
	}
	@Override
	public List<Etudiant> opListe() {
		Etudiant etudiant = new Etudiant();
		etudiant.setNom("Bon");
		etudiant.setPrenom("Jean");
		etudiant.setPromo("gsi");
		Etudiant etudiant2 = new Etudiant();
		etudiant2.setNom("Bon2");
		etudiant2.setPrenom("Jean2");
		etudiant2.setPromo("gsi2");
		Etudiant etudiant3 = new Etudiant();
		etudiant3.setNom("Bon3");
		etudiant3.setPrenom("Jean3");
		etudiant3.setPromo("gsi3");
		List<Etudiant> liste=new ArrayList<Etudiant>();
		liste.add(etudiant);
		liste.add(etudiant2);
		liste.add(etudiant3);
		return liste;
	}
	
/*	@Override
	public List<Personne> op2(Personne p1, Personne p2) {
		listePersonne.add(p1);
		if(p2!=null)listePersonne.add(p2);
		return this.listePersonne;
	}*/


	
	@Override
	public String getPersonne(int id) {
		int i=trouverPersonne(id);
		if(i==-1){
			return "aucune personne ne possède cet identifiant:"+id;
		}
		return ""+UniformementRepresentable.toString(new StringBuilder(), listePersonne.get(i));
	}
	
	@Override
	public String listePersonne() {
		int size=listePersonne.size();
		if(size>0){
			String s="DEBUT LISTE:";
			for(int i=0;i<size;i++){
				s+="\n"+UniformementRepresentable.toString(new StringBuilder(), listePersonne.get(i));
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
		int i=trouverPersonne(id);
		if(i==-1){
			return "aucune personne ne possède cet identifiant:"+id;
		}
		listePersonne.get(i).setNom(nom);
		return "modification effectué";
	}
	
	@Override
	public String modificationPrenomPersonne(int id, String prenom) {
		int i=trouverPersonne(id);
		if(i==-1){
			return "aucune personne ne possède cet identifiant:"+id;
		}
		listePersonne.get(i).setPrenom(prenom);
		return "modification effectué";
	}
	
	@Override
	public String ajouterPersonne(Personne p) {
		int i=trouverPersonne(p.getId());
		if(i!=-1){
			return "Une personne possède déjà cet identifiant:"+p.getId();
		}
		listePersonne.add(p);
		return "La personne suivante à été ajouté à la liste: \n"+UniformementRepresentable.toString(new StringBuilder(), p);
	}
	
	@Override
	public String effacerPersonne(int id) {
		int i=trouverPersonne(id);
		if(i==-1){
			return "aucune personne ne possède cet identifiant:"+id;
		}
		listePersonne.remove(i);
		return "La personne avec l'id:"+id+" a été supprimé";
	}
	
	private int trouverPersonne(int id){
		int size=listePersonne.size();
		if(size==0) return -1;
		int i=0;
		boolean a=true;
		do{
			if(listePersonne.get(i).getId()==id){
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
	public String op2(Etudiant p) {
		// TODO Auto-generated method stub
		return null;
	}






}

