package example.services;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import example.model.Person;
import example.model.PersonImpl;
import example.model.PersonWithoutFieldImpl;
import example.model.TeacherImpl;

public class Resources implements Filter {
	
	private static boolean init=false;
	private static Hashtable<Integer, Person> persons=new Hashtable<Integer, Person>();
	
    private static void init(){
    	PersonImpl p=new PersonImpl();
		p.setFirstName("Luke");
		p.setLastName("Lucky");
		persons.put(0, p);
		PersonWithoutFieldImpl p2=new PersonWithoutFieldImpl();
		p2.setFirstName("Darth");
		p2.setLastName("Vader");
		persons.put(1, p2);
		TeacherImpl p3=new TeacherImpl();
		p3.setFirstName("Bruce");
		p3.setLastName("Wayne");
		persons.put(2, p3);
		init=true;
    }
    public static Person getPerson(int i){
    	return Resources.persons.get(i);
    }
    public static int postPerson(Person p){
    	Enumeration<Integer> it = Resources.persons.keys();
		int max=-1;
		int current=0;
		while(it.hasMoreElements()){
			current=it.nextElement();
			if(current>=max) max=current+1;
		}
		if(current!=-1) putPerson(p, max);
		return max;
    }
    public static void putPerson(Person p, int i){
    	Resources.persons.put(i, p);
    }
    public static Person deletePerson(int i){
    	return Resources.persons.remove(i);
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(!init){
        	Resources.init();
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            //localRequest.remove();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}