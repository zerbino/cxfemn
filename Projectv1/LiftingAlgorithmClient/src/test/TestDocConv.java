package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.print.Doc;

import lifting.conversion.StreamConversion;
import lifting.conversion.StreamConversionImp;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class TestDocConv {

	private StreamConversion streamConv = new StreamConversionImp();

	public InputStream xml2Stream(Document doc) {
		return streamConv.doc2Stream(doc);

	}

	public Document stream2Xml(InputStream input) {
		return streamConv.stream2Doc(input);
	}

	public Document interdepTest(Document doc) {
		return  stream2Xml(xml2Stream(doc));
		

	}

	

	public Document build(File file) {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(file);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}

		return doc;
	}

	public void xmlConvTest() throws FileNotFoundException {
		File file = new File("documents/NinoLabruti.xml");
		Document doc = this.build(file);
		
		//test interdépendance
		//Document doc1 = interdepTest(doc);
		
		//test stream to xml
		InputStream input = new FileInputStream(file);
		Document doc2 = this.streamConv.stream2Doc(input); 
		
		//test xml to stream
		InputStream stream =this.streamConv.doc2Stream(doc2);
		
		//and again
		Document doc3 = this.streamConv.stream2Doc(stream);
		
		Variables.printDoc(doc);
		Variables.printDoc(doc2);
		Variables.printDoc(doc3);
	}

	public static void main(String[] args) {
		TestDocConv test = new TestDocConv();
		try {
			test.xmlConvTest();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
