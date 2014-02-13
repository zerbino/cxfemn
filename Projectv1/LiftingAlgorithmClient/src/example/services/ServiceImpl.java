package example.services;

import java.util.List;

import example.model.Person;
import example.model.PersonImpl;
import example.model.PersonWithoutFieldImpl;

public class ServiceImpl implements Service{
	private Resources resources;
	
	@Override
	public int post_PersonImpl(PersonImpl p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int post_Person(Person p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> post_2_PersonImpl(PersonImpl p1, PersonImpl p2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> post_2_Person(Person p1, Person p2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putLastName_Person(int id, String lastName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person delete_Person(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonImpl get_PersonImpl(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Person get_Person(int id) {
		Resources r=new Resources();
		System.out.println(r.getSession().getAttribute(id+""));
		return (Person) r.getSession().getAttribute(id+"");
	}

	@Override
	public List<PersonImpl> getAll_PersonImpl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getAll_Person() {
		// TODO Auto-generated method stub
		return null;
	}


}
