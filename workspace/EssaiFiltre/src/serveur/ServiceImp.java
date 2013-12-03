package serveur;

import javax.ws.rs.core.Response;

import client.Personne;

public class ServiceImp implements Service {
	
	Personne personne;
	
	public ServiceImp() {
		this.personne = new Personne();
		personne.setNom("Henry-Séguin");
		personne.setPrenom("Gergoire");
	}
	
	public Response getPersonne(){
		return Response.ok(personne).build();
	}
	

}
