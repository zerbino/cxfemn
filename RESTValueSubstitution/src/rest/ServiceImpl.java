package rest;

public class ServiceImpl implements Service {

	@Override
	public String op(Personne p) {
		return p.getNom();
	}

}
