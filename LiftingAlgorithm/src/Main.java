import java.util.*;

import javax.xml.bind.*;
import javax.xml.bind.util.*;
import javax.xml.validation.*;

import ServerLifter.XSDLifter;
import utile.UniformementRepresentable;
import model.Etudiant;
import model.ObjectFactory;
import model.Personne;

import java.io.*;

public class Main{

	public static void main(String[] args) {
		Etudiant e=new Etudiant();
		e.setNom("Labruti");
		e.setPrenom("Nino");
		e.setPromo("Alien");
		//I had to manually modify the model:
		//replacing the root Personne element by Etudiant
		XSDLifter.metaSchema="documents/model.xsd";
		XSDLifter.Lift((Personne) e,"documents/NinoLabruti.xml");
	}
}