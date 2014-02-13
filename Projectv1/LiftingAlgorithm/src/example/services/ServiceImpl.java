package example.services;

import java.util.Enumeration;
import java.util.List;

import example.model.Person;
import example.model.PersonImpl;
import example.model.PersonWithoutFieldImpl;
import example.model.TeacherImpl;

public class ServiceImpl implements Service{
	@Override
	public int post_PersonImpl(PersonImpl p) {
		return post_Person(p);
	}

	@Override
	public int post_Person(Person p) {
		return Resources.postPerson(p);
	}

	@Override
	public int post_PersonWithoutFieldImpl(PersonWithoutFieldImpl p) {
		return post_Person(p);
	}
//	
//	@Override
//	public List<Integer> post_2_PersonImpl(PersonImpl p1, PersonImpl p2) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Integer> post_2_Person(Person p1, Person p2) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void put_PersonImpl(int id, PersonImpl p) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public TeacherImpl post_PromotePerson(Person p) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Person delete_Person(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PersonImpl get_PersonImpl(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
	@Override
	public Person get_Person(int id) {
		//return (Person) Resources.getSession().getAttribute(id+"");
		return Resources.getPerson(id);
	}
//
//	@Override
//	public List<PersonImpl> getAll_PersonImpl() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Person> getAll_Person() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
