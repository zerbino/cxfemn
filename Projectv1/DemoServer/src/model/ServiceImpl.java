package model;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import tools.UniformementRepresentable;

//@InInterceptors(interceptors = {"interceptor.ClientRequestInterceptor"})
public class ServiceImpl implements Service {

	@Override
	public String op(PersonneImpl p) {
		return p.getPrenom();
	}
	
	@Override
	public String opInt(Personne p) {
		
		return p.getPrenom();
	}

	@Override
	public EtudiantImpl op() {
		EtudiantImpl etudiant = new EtudiantImpl();
		etudiant.setNom("Bon");
		etudiant.setPrenom("Jean");
		etudiant.setPromo("gsi");
		return etudiant;
	}
	
	@Override
	public Etudiant opInt() {
		Etudiant etudiant = new EtudiantImpl();
		etudiant.setNom("Bon");
		etudiant.setPrenom("Jean");
		etudiant.setPromo("gsi");
		return etudiant;
	}
	
}

