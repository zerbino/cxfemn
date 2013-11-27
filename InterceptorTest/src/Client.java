import java.util.*;

import javax.xml.bind.*;
import javax.xml.bind.util.*;
import javax.xml.namespace.QName;
import javax.xml.validation.*;
import javax.xml.ws.Service;

import org.apache.cxf.frontend.ClientProxy;

import utile.MyInterceptor;
import utile.UniformementRepresentable;
import model.ObjectFactory;
import model.Personne;

import java.io.*;

public class Client{

	public static void main(String[] args) {
		//attention il y a deux classes service! une du package ws et une à nous
		QName serviceName = new QName("http://demo.eric.org", "MyInterceptor"); 
		Service s = Service.create(serviceName);

		QName portName = new QName("http://demo.eric.org", "MyInterceptorPort"); 
		s.addPort(portName, "http://schemas.xmlsoap.org/soap/", "http://localhost:80/Example");

		model.Service proxy = s.getPort(portName, model.Service.class);

		org.apache.cxf.endpoint.Client cxfClient = ClientProxy.getClient(proxy);

		MyInterceptor validInterceptor = new MyInterceptor();
		cxfClient.getInInterceptors().add(validInterceptor);
		Test t=new Test("model","documents","Entree.xml","Sortie.xml");
		ObjectFactory fabrique = new ObjectFactory();
		Personne p = t.personne("Carl", "Johnson");
		try {
			System.out.println(UniformementRepresentable.toString(new StringBuilder(), p));
			JAXBElement<Personne> enveloppe1 = fabrique.createPersonne(p);
			FileOutputStream out = new FileOutputStream(t.REP + "/" + t.FICHIER1);
			t.marshall(enveloppe1, out);
			FileInputStream in = new FileInputStream(t.REP + "/" + t.FICHIER1);
			Object CGFp = t.unmarshall(in);

			out = new FileOutputStream(t.REP + "/" + t.FICHIER2);
			t.marshall(p, out);
			in = new FileInputStream(t.REP + "/" + t.FICHIER2);
			Object FCGp = t.unmarshall(in);

			System.out.println("CGFp: "
					+ UniformementRepresentable.toString(new StringBuilder(),
							((JAXBElement<?>) (CGFp)).getValue()));
			System.out.println("FCGp: "
					+ UniformementRepresentable.toString(new StringBuilder(),
							((JAXBElement<?>) (FCGp)).getValue()));
			System.out.println("Are these objects equal? "
					+ UniformementRepresentable.equals(CGFp, FCGp));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}