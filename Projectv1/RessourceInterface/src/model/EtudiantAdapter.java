package model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class EtudiantAdapter extends XmlAdapter<EtudiantImpl, Etudiant>{

	@Override
	public EtudiantImpl marshal(Etudiant v) throws Exception {
		return null;
	}

	@Override
	public Etudiant unmarshal(EtudiantImpl v) throws Exception {
		return v;
	}

}
