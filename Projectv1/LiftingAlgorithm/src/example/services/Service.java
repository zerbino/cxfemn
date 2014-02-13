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

import example.model.Person;
import example.model.PersonImpl;
import example.model.PersonAdapter;
import example.model.PersonWithoutFieldImpl;
import example.model.TeacherImpl;
import filter.server.AllowSubstitution;
/**
 * These example services aims at illustrate our solution behaviour
 * with different rest methods and different kind of arguments.
 *
 */
@Path("/rest")
public interface Service {
	/*
	 * Algorithm test server-side.
	 * In the following cases, we'll receive subtypes
	 * of the expected types from client.
	 */
	/**
	 * Restful: a resource needs to be created,
	 * we create it then reply with its id.
	 * @param PersonImpl
	 * @return int
	 */
	@AllowSubstitution
	@POST
	@Path("/post_personimpl")
	@Produces(MediaType.TEXT_PLAIN)
	public int post_PersonImpl(PersonImpl p);
	
	/**
	 * Restful: a resource needs to be created,
	 * we create it then reply with its id.
	 * This case show the need for a specific annotation when
	 * parameters are interfaces.
	 * @param Person
	 * @return int
	 */
	@AllowSubstitution
	@POST
	@Path("/post_person")
	@Produces(MediaType.TEXT_PLAIN)
	public int post_Person(@XmlJavaTypeAdapter(PersonAdapter.class)Person p);
	/**
	 * Restful: a resource needs to be created,
	 * we create it then reply with its id.
	 * Algorithm used to fail checking parameters fields
	 * when expected objects only have inherited fields.
	 * @param Person
	 * @return int
	 */
	@AllowSubstitution
	@POST
	@Path("/post_personwithoutfieldimpl")
	@Produces(MediaType.TEXT_PLAIN)
	public int post_PersonWithoutFieldImpl(PersonWithoutFieldImpl p);
	
//	@AllowSubstitution
//	@POST
//	@Path("/post_2_personimpl")
//	@Produces(MediaType.APPLICATION_XML)
//	public List<Integer> post_2_PersonImpl(PersonImpl p1, 
//			PersonImpl p2);
//	
//	@AllowSubstitution
//	@POST
//	@Path("/post_2_person")
//	@Produces(MediaType.APPLICATION_XML)
//	public List<Integer> post_2_Person(@XmlJavaTypeAdapter(PersonAdapter.class)Person p1, 
//			@XmlJavaTypeAdapter(PersonAdapter.class)Person p2);
//
//	@PUT
//	@Path("/put_personimpl/{id}")
//	@Produces(MediaType.APPLICATION_XML)
//	public void put_PersonImpl(@PathParam("id") int id, PersonImpl p);
//	
//	/*
//	 * Algorithm test client-side
//	 */
//	@AllowSubstitution
//	@POST
//	@Path("/post_person")
//	@Produces(MediaType.APPLICATION_XML)
//	public TeacherImpl post_PromotePerson(@XmlJavaTypeAdapter(PersonAdapter.class)Person p);
//	
//	@DELETE
//	@Path("/delete_person/{id}")
//	@Produces(MediaType.APPLICATION_XML)
//	public Person delete_Person(@PathParam("id") int id);
//
//	@GET
//	@Path("/getpersonimpl/{id}")
//	@Produces(MediaType.APPLICATION_XML)
//	public PersonImpl get_PersonImpl(@PathParam("id") int id);
//
	@GET
	@Path("/getperson/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public @XmlJavaTypeAdapter(PersonAdapter.class)Person get_Person(@PathParam("id") int id);
//	
//	@GET
//	@Path("/getall_personimpl")
//	@Produces(MediaType.APPLICATION_XML)
//	public List<PersonImpl> getAll_PersonImpl();
//	
//	@GET
//	@Path("/getall_person")
//	@Produces(MediaType.APPLICATION_XML)
//	public @XmlJavaTypeAdapter(PersonAdapter.class)List<Person> getAll_Person();
//	
}

