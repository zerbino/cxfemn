package test;

import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;

public class Variables {
	
	/**
	 * path to an xml file for tests
	 */
	public static String pathXML = "documents/NinoLabruti.xml";
	
	public static void printDoc(Document doc) {
		System.out.println(new XMLOutputter().outputString(doc));

	}

}
