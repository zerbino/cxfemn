package model;

import javax.jws.WebService;


public class ServiceImpl implements Service {

	@Override
	public String op(Personne p) {
		return p.getNom();
	}

}
