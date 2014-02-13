package example.services;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import example.model.PersonImpl;
import example.model.PersonWithoutFieldImpl;
import example.model.TeacherImpl;

public class Resources implements Filter {

    private static ThreadLocal<HttpServletRequest> localRequest = new ThreadLocal<HttpServletRequest>();


    public static HttpServletRequest getRequest() {
        return localRequest.get();
    }

    public static HttpSession getSession() {
        HttpServletRequest request = localRequest.get();
        if(request !=null && request.getSession() !=null && !request.getSession().getAttributeNames().hasMoreElements()){
        	System.out.println("init db");
			PersonImpl p=new PersonImpl();
			p.setFirstName("Luke");
			p.setLastName("Lucky");
			request.getSession().setAttribute(0+"", p);
			PersonWithoutFieldImpl p2=new PersonWithoutFieldImpl();
			p2.setFirstName("Darth");
			p2.setLastName("Vader");
			request.getSession().setAttribute(1+"", p2);
			TeacherImpl p3=new TeacherImpl();
			p3.setFirstName("Bruce");
			p3.setLastName("Wayne");
			request.getSession().setAttribute(2+"", p3);
		}
        return (request != null) ? request.getSession() : null;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            localRequest.set((HttpServletRequest) servletRequest);
        }

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            localRequest.remove();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}