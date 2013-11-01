package client;

import rest.Personne;

public class Etudiant extends Personne {
	protected String promo;
	public String getPromo() {
		return promo;
	}
	public void setPromo(String promo) {
		this.promo = promo;
	}
	public Etudiant() {
		super();
	}
}
