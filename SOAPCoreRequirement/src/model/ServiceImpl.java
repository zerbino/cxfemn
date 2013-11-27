package model;

import javax.jws.WebService;

@WebService(targetNamespace = "http://model/", portName = "ServiceImplPort", serviceName = "ServiceImplService")
public class ServiceImpl implements Service {

	@Override
	public String op(Personne p) {
		return p.getNom();
	}

}
