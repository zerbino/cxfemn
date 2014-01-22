package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import model.Personne;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import serverLifter.archi.LifterCaller;
import serverLifter.archi.Lifting;
import serverLifter.archi.ServerLifterCaller;
import serverLifter.archi.ServerLifting;

public class TestServerArchitecture {

	private static File testFile = new File(Variables.pathXML);

	/**
	 * Returns a Document instance from a file.
	 * @param file
	 * @return the Document instance
	 * @throws JDOMException
	 * @throws IOException
	 */
	public Document docFromFile(File file) throws JDOMException, IOException{
		SAXBuilder builder = new SAXBuilder();
		return builder.build(testFile);
	}
	
	/**
	 * Tests the behavior of ClientLifting class
	 * @param initDoc
	 * @param clazz
	 * @return the lifted Document
	 */
	public Document docLifted(Document initDoc, Class<?>[] clazz) {
		Lifting clientLifting = new ServerLifting(initDoc, clazz);
		return clientLifting.lifting();
	}


	/**
	 * Tests the behavior of ClientLifterCaller class
	 * @return the lifted Document
	 * @throws FileNotFoundException
	 */
	public Document liftingCallerTest() throws FileNotFoundException {
		InputStream input = new FileInputStream(testFile);
		Class<?>[] classes = new Class<?>[1];
		classes[0] = Personne.class;
		LifterCaller clientLifterCaller = new ServerLifterCaller(input,
				classes);
		return clientLifterCaller.call();
	}

	/**
	 * Test calling liftingTest and liftingCallerTest methods. Makes sure that
	 * what's produced by the lifting class and the lifterCaller class is the same
	 * and is what's expected.
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void testFinal() throws JDOMException, IOException {
		//Initial document
		Document initDoc = docFromFile(testFile);
		System.out.println("Initial document :");
		Variables.printDoc(initDoc);
		
		Class<?>[] classes = new Class<?>[1];
		classes[0] = Personne.class;
		Document doc = docLifted(initDoc, classes);
		Document docCaller = liftingCallerTest();

		System.out.println("Document obtained with ClientLifting :");
		Variables.printDoc(doc);

		System.out.println("Document obtained with ClientLifterCaller :");
		Variables.printDoc(docCaller);
	}

	public static void main(String[] args) {
		TestServerArchitecture test = new TestServerArchitecture();
		try {
			test.testFinal();
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}

}
