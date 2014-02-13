package example.services;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import example.model.EtudiantAdapter;
import example.model.Person;
import example.model.PersonImpl;
import example.model.PersonAdapter;
import example.model.PersonWithoutFieldImpl;
import example.model.Student;
import example.model.StudentImpl;
/**
 * These example services aims at illustrate the different rest 
 * methods used with different kind of arguments.
 *
 */
@Path("/rest")
public interface Service {
	
	
	@POST
	@Path("/post_personimpl")
	@Produces(MediaType.APPLICATION_XML)
	public int post_PersonImpl(PersonImpl p);
	
	
	@POST
	@Path("/post_person")
	@Produces(MediaType.APPLICATION_XML)
	public int post_Person(@XmlJavaTypeAdapter(PersonAdapter.class)Person p);

	
	@POST
	@Path("/post_2_personimpl")
	@Produces(MediaType.APPLICATION_XML)
	public List<Integer> post_2_PersonImpl(PersonImpl p1, 
			PersonImpl p2);
	
	
	@POST
	@Path("/post_2_person")
	@Produces(MediaType.APPLICATION_XML)
	public List<Integer> post_2_Person(@XmlJavaTypeAdapter(PersonAdapter.class)Person p1, 
			@XmlJavaTypeAdapter(PersonAdapter.class)Person p2);
	
	@PUT
	@Path("/putlastname_person/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public void putLastName_Person(@PathParam("id") int id, String lastName);
	
	@DELETE
	@Path("/delete_person/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Person delete_Person(@PathParam("id") int id);

	@GET
	@Path("/getpersonimpl/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public PersonImpl get_PersonImpl(@PathParam("id") int id);

	@GET
	@Path("/getperson/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public @XmlJavaTypeAdapter(PersonAdapter.class)Person get_Person(@PathParam("id") int id);
	
	@GET
	@Path("/getall_personimpl")
	@Produces(MediaType.APPLICATION_XML)
	public List<PersonImpl> getAll_PersonImpl();
	
	@GET
	@Path("/getall_person")
	@Produces(MediaType.APPLICATION_XML)
	public @XmlJavaTypeAdapter(PersonAdapter.class)List<Person> getAll_Person();

}

