package client.bugs.cxf;


import model.EtudiantImpl;
import model.Service;
import user.ClientServerFilter;

/**
 * 
 * This class tests the behavior of CXF when the incoming object is a subtype of what's excepted server side.
 * The particularity is that the object is passed thru a GET request. 
 * 
 * @author raphael
 *
 */
public class TestClient7 {
	
	public static void main(String[] args) {
		Service service = new ClientServerFilter().getService();
		EtudiantImpl p = new EtudiantImpl();
		p.setId(1);
		p.setNom("Martignoni");
		p.setPrenom("Raphael");
		p.setPromo("GSI");
		String nom =service.opGet(p);
		System.out.println(nom);
	}

}
