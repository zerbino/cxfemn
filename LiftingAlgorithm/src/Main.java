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
		new XSDLifter().HTTPAdapter();
	}
}