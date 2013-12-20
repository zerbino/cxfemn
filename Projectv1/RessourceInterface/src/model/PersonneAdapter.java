package model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PersonneAdapter extends XmlAdapter<PersonneImpl, Personne>{

	@Override
	public PersonneImpl marshal(Personne arg0) throws Exception {
		return null;
	}

	@Override
	public Personne unmarshal(PersonneImpl arg0) throws Exception {
		return arg0;
	}

}
