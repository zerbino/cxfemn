package rest;

import org.apache.cxf.interceptor.InInterceptors;

import models.Personne;
@InInterceptors(interceptors = {"interceptors.ServerInterceptor"})
public class ServiceImpl implements Service {

	@Override
	public String op(Personne p) {
		return p.getNom();
	}

}
