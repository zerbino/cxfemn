package sandboxTest;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import model.Service;










import utile.UniformementRepresentable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.ws.rs.core.Response;

import model.Etudiant;
import model.Personne;


public class TestClient {
	
	public static void main(String[] args) throws Exception {
		Service serv = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class);
		//Etudiant e = serv.op();
		//System.out.println(e.getPrenom());
		Personne p = serv.op2();
		System.out.println(p.getPrenom());
	}
}
