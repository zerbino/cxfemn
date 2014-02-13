package example.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PersonAdapter extends XmlAdapter<PersonImpl, Person>{

	@Override
	public PersonImpl marshal(Person arg0) throws Exception {
		return (PersonImpl)arg0;
	}

	@Override
	public Person unmarshal(PersonImpl arg0) throws Exception {
		return arg0;
	}

}
