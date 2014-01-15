package lifting.conversion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.transform.JDOMSource;

public class StreamConversionImp implements StreamConversion {

	@Override
	public Document stream2Doc(InputStream input) {
		SAXBuilder sxb = new SAXBuilder();
		Document document=null;
		try {
			document = sxb.build(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return document;
	}

	@Override
	public InputStream doc2Stream(Document doc) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source xmlSource = new JDOMSource(doc);
		Result outputTarget = new StreamResult(outputStream);
		try {
			TransformerFactory.newInstance().newTransformer()
					.transform(xmlSource, outputTarget);
		} catch (TransformerException
				| TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream is = new ByteArrayInputStream(outputStream.toByteArray());

		return is;
	}

}
