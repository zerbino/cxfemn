package rest;

import models.Animal;
import models.Personne;

public class ServiceImpl implements Service {

	@Override
	public String op(Personne p) {
		return p.getNom();
	}

	@Override
	public String pseudo(Animal a) {
		return a.getPseudo();
	}
	
	

}
