package client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Personne {
	
	private String nom;
	
	private String prenom;

	

	public Personne() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String toString(){
		return this.getNom()+" "+this.getPrenom();
	}

}
