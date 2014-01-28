package lifting;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import model.Personne;
import model.PersonneImpl;

public class PersonneAdapter extends XmlAdapter<PersonneImpl, Personne>{

	@Override
	public PersonneImpl marshal(Personne arg0) throws Exception {
		return (PersonneImpl)arg0;
	}

	@Override
	public Personne unmarshal(PersonneImpl arg0) throws Exception {
		return arg0;
	}

}
