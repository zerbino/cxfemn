package client.bugs.cxf;

import model.ClientModel;
import model.EtudiantImpl;
import model.Service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

/**
 * This client tests whether CXF raises an exception when the client is sending
 * a subtype of what is excepted by the server as incoming object. The subtype
 * sent is unknown server-side
 * 
 * @author raphael
 * 
 */
public class TestClient6 {

	public static void main(String[] args) {
		Service service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class);
		ClientModel clientModel = new ClientModel();
		clientModel.setId(1);
		clientModel.setNom("Bon");
		clientModel.setPrenom("Jean");
//		String returnedString = service.op(clientModel);
//		System.out.println(returnedString);
	}

}
