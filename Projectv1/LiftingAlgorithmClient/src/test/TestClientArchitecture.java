package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import lifting.ClientLifterCaller;
import lifting.ClientLifting;
import model.Personne;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class TestClientArchitecture {
	
	public Document docLifted(Document initDoc, Class<?> clazz){
		ClientLifting clientLifting = new ClientLifting(initDoc, clazz);
		return clientLifting.lifting();
	}
	
	public Document liftingTest() throws JDOMException, IOException{
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		doc = builder.build(new File(Variables.pathXML));
		this.docLifted(doc, Personne.class);
		return doc;
	}
	
	public Document liftingCallerTest() throws FileNotFoundException{
		InputStream input = new FileInputStream(new File(Variables.pathXML));
		ClientLifterCaller clientLifterCaller=new ClientLifterCaller(input, Personne.class);
		return clientLifterCaller.call();
	}
	
	public void testFinal() throws JDOMException, IOException{
		Document doc = liftingTest();
		Document docCaller = liftingCallerTest();
		
		System.out.println("Document obtenu par ClientLifting :");
		Variables.printDoc(doc);
		
		System.out.println("Document obtenur par ClientLifterCaller :");
		Variables.printDoc(docCaller);
	}
	
	public static void main(String[] args) {
		TestClientArchitecture test = new TestClientArchitecture();
		try {
			test.testFinal();
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}

}
