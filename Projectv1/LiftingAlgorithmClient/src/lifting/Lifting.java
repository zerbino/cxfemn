package lifting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.transform.JDOMSource;

/**
 * This class is used to make sure that an entity (an InputStream), corresponds
 * to the entity which is expected. It can be extended when the client requests
 * on the server. In this case, the resource must be compared to the the
 * arguments of the service. Or when the server responds to the client. In this
 * case, the resource is compared to what the client is expecting, and to what
 * the server is sending.
 * 
 * 
 */
public abstract class Lifting {

	protected Document document;
	protected Element racine;
	protected String outputPath;
	protected Method service;

	public Lifting(InputStream ressource, Method m) {
		this.initializeOutPutPath();
		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(ressource);
		} catch (Exception e) {
			e.printStackTrace();
		}

		racine = document.getRootElement();
		service = m;
	}

	private void initializeOutPutPath() {
		Random r = new Random();
		int randHash = r.nextInt() * 100;
		if (randHash < 0)
			randHash = -randHash;
		this.outputPath = getClass().getClassLoader().getResource(".")
				.getPath()
				+ "FAKEDocuments/" + randHash + "ParameterMODIFIED.xml";
	}

	public Document Lift() {
		try {
			System.out.println("Before: ");
			print(racine);
			treeInclusion();
			System.out.println("After: ");
			print(racine);
			SAXBuilder sxb = new SAXBuilder();
			return sxb.build(new File(outputPath));
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Is specified for the case of a request to the server, or a response to
	 * the client. The element to compare are different in both cases.
	 */
	protected abstract void treeInclusion();

	public void print(Element e) {
		List<Element> l = e.getChildren();
		Iterator<Element> i = l.iterator();
		System.out.println(racine.getName());
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			System.out.print("	" + courant.getName());
			if (courant.getChildren().size() != 0)
				print(courant);
			else {
				if (courant.getValue() != null) {

					System.out.println(": " + courant.getValue());
				}
			}
		}
	}
	//remove fields that doesn't match any of those in the super class
	// and check that mandatory fields are present
	public boolean removeExtraFields(Element e, Class<?> class1) {
		Field[] f = class1.getDeclaredFields();
		boolean b;
		List<Element> l = e.getChildren();
		Iterator<Element> i = l.iterator();
		int mandatoryFields=0;
		while (i.hasNext()) {
			Element courant = i.next();
			b = true;
			for (int j = f.length - 1; j >= 0; j--) {
				if (f[j].getName().equals(courant.getName())) {
					b = false;
					mandatoryFields++;
					break;
				}
			}
			if (b) {
				e.removeContent(courant);
			}
		}
		
		if(f.length==mandatoryFields){

		try {
			enregistrefichier();
		} catch (Exception e1) {
			e1.printStackTrace();

		}
			return true;
		}
		else{
			return false;
		}
	}

	protected void rename(Element e, String name) {
		e.setName(name);
	}

	public void enregistrefichier() throws Exception {
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		File f = new File(outputPath);
		if (!f.exists()) {
			f.getParentFile().mkdirs();
		}
		sortie.output(document, new FileOutputStream(outputPath));
	}

	public static void enregistrefichier(Element document, String outputPath)
			throws Exception {
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		File f = new File(outputPath);
		if (!f.exists()) {
			f.getParentFile().mkdirs();
		}
		sortie.output(document, new FileOutputStream(outputPath));
	}

	public InputStream lifting() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source xmlSource = new JDOMSource(this.Lift());
		Result outputTarget = new StreamResult(outputStream);
		try {
			TransformerFactory.newInstance().newTransformer()
					.transform(xmlSource, outputTarget);
		} catch (Exception e) {

		}
		return new ByteArrayInputStream(outputStream.toByteArray());
	}

}
