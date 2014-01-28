package client;
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

import model.EtudiantImpl;
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
	public static String marshall(
			Object o) {
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema =null;
			schema = factory.newSchema(new File("documents/model.xsd"));
			JAXBContext jc = JAXBContext.newInstance("model");
			Marshaller m = jc.createMarshaller();
			m.setSchema(schema);
			//I noticed here you need to add tools.jar from the jdk to make the generation to jaxb classes possible
			m.marshal(o, new FileOutputStream(new File("documents/test.xml")));
			String chaine="";
			String fichier ="documents/test.xml";
			
			//lecture du fichier texte	
			try{
				InputStream ips=new FileInputStream(fichier); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					chaine+=ligne+"\n";
				}
				br.close(); 
				return chaine;
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {

		Etudiant p= new EtudiantImpl();
		p.setNom("Op-AFaitSonJob");
		p.setPrenom("Kevin");
		p.setPromo("gsi");
		String xml=marshall(p);
		
		String url = "http://localhost:8080/LiftingAlgorithm/rest/op";
		//doesn't work
		 /*Service op=JAXRSClientFactory.create( url, Service.class);
		 System.out.println(op.op(p));*/
		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
	 
		// add header
		//post.setHeader("User-Agent", USER_AGENT);
	 
		post.setHeader("Content-Type", "application/xml");
		post.setEntity(new StringEntity(xml));//urlParameters));
		
		HttpResponse response = client.execute(post);
		System.out.println("Response Code : " 
	                + response.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader(
		        new InputStreamReader(response.getEntity().getContent()));
	 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result.toString());

	}
}
