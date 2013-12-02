package rest;

import models.Personne;

public class ServiceImpl implements Service {

	@Override
	public String op(Personne p) {
		return p.getNom();
	}
	
	

}
