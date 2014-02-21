package model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class EtudiantAdapter extends XmlAdapter<TeacherImpl, Teacher>{

	@Override
	public TeacherImpl marshal(Teacher v) throws Exception {
		return (TeacherImpl)v;
	}

	@Override
	public Teacher unmarshal(TeacherImpl v) throws Exception {
		return v;
	}

}
