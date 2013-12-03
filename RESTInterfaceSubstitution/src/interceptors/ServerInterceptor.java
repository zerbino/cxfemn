/**
 * @author: Hao ZHANG
 * analyze the received message and change the root element.
 */

package interceptors;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.cxf.attachment.AttachmentDeserializer;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ServerInterceptor extends AbstractPhaseInterceptor<Message> {

	public ServerInterceptor(String phase) {
		super(phase);
	}
	
	public ServerInterceptor() {
		this(Phase.PRE_UNMARSHAL);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		System.out.println("zh adds "+this.getPhase());
        InputStream is = (InputStream)message.getContent(InputStream.class);
		//String xmlString = getStringFromInputStream(is);
		//System.out.println(xmlString);
		// deal with the xml message.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document xmlDocu = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		try {
			xmlDocu = builder.parse(is);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element root = xmlDocu.getDocumentElement();
		NodeList nodes = root.getChildNodes();
		// change the name of the root element.
		xmlDocu.renameNode(root, null, "personne");
		System.out.println(Doc2Str(xmlDocu));
        message.setContent(InputStream.class, str2inputstream(Doc2Str(xmlDocu)));
        
	}
	
	public static InputStream str2inputstream(String str){
		return new ByteArrayInputStream(str.getBytes());
	}
	public static String getStringFromInputStream(InputStream is) {
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
 
	}
	
	public static String Doc2Str(Document doc) {
        TransformerFactory transFactory=TransformerFactory.newInstance();
        try {
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
            return output;
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
		return null;
    }
 
}
