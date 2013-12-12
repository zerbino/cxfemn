package djaxb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.eclipse.persistence.jaxb.JAXBMarshaller;
import org.eclipse.persistence.jaxb.JAXBUnmarshaller;
import org.eclipse.persistence.jaxb.dynamic.DynamicJAXBContext;
import org.eclipse.persistence.jaxb.dynamic.DynamicJAXBContextFactory;

public class Djaxb {
	private DynamicJAXBContext djContext;
	private JAXBMarshaller marshaller;
	private JAXBUnmarshaller unmarshaller;
	
	public Djaxb(String xsdFile){
		InputStream is=null;
		try {
			is = new FileInputStream(xsdFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			djContext = DynamicJAXBContextFactory.createContextFromXSD(is, null, null, null);
			marshaller = djContext.createMarshaller();
			unmarshaller = djContext.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
	public DynamicJAXBContext getJAXBContext(){
		return djContext;
	}
	
	public JAXBMarshaller getMarshaller(){
		return marshaller;
	}
	
	public JAXBUnmarshaller getUnmarshaller(){
		return unmarshaller;
	}
}
