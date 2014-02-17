package model;

//@InInterceptors(interceptors = {"interceptor.ClientRequestInterceptor"})
public class ServiceImpl implements Service {

	@Override
	public String op(PersonImpl p) {
		return p.getFirstname();
	}
	
	@Override
	public String opInt(Person p) {
		
		return p.getFirstname();
	}

	@Override
	public StudentImpl op() {
		StudentImpl student = new StudentImpl();
		student.setLastname("Bon");
		student.setFirstname("Jean");
		student.setMajor("gsi");
		return student;
	}
	
	@Override
	public Student opInt() {
		Student student = new StudentImpl();
		student.setLastname("Bon");
		student.setFirstname("Jean");
		student.setMajor("gsi");
		return student;
	}
	
}

