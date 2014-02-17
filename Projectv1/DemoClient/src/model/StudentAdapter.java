package model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class StudentAdapter extends XmlAdapter<StudentImpl, Student>{

	@Override
	public StudentImpl marshal(Student v) throws Exception {
		return (StudentImpl)v;
	}

	@Override
	public Student unmarshal(StudentImpl v) throws Exception {
		return v;
	}

}
