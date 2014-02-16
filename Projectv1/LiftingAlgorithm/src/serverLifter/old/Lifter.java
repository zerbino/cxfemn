package serverLifter.old;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.jdom2.transform.JDOMSource;

import filter.server.Tools;
import tools.UniformementRepresentable;

public class Lifter {
	// for sandbox tests
	public static String metaSchema;

	public static void marshall(Object o, String outputFile) {
		FileOutputStream out = null;
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema = null;
			out = new FileOutputStream(outputFile);
			schema = factory.newSchema(new File(metaSchema));
			JAXBContext jc = JAXBContext.newInstance("model");
			Marshaller m = jc.createMarshaller();
			m.setSchema(schema);
			// I noticed here you need to add tools.jar from the jdk to make the
			// generation to jaxb classes possible
			m.marshal(o, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InputStream HTTPAdapter(InputStream in, Method method) {
		try {
			return HTTPAdapterExcept(in, method);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return in;
	}

	private InputStream HTTPAdapterExcept(InputStream in, Method method)
			throws IOException {
		String tmp = Tools.inputStreamToString(in);
		return this.HTTPAdapter(tmp, method);
	}

	// Add the HTTP request content as parameter
	public InputStream HTTPAdapter(String s, Method m) {
		try {
			Random r = new Random();
			// FIXME
			// this is dirty. We should count the number of files in the folder
			// and
			// name the file accordingly
			int randHash = r.nextInt() * 100;
			if (randHash < 0)
				randHash = -randHash;

			String path = getClass().getClassLoader().getResource(".")
					.getPath()
					+ "FAKEDocuments/" + randHash + "Parameter.xml";
			String pathModified = getClass().getClassLoader().getResource(".")
					.getPath()
					+ "FAKEDocuments/" + randHash + "ParameterMODIFIED.xml";
			File f = new File(path);
			if (!f.exists()) {
				f.getParentFile().mkdirs();
			}
			Charset charset = Charset.forName("UTF-8");
			Writer writer = new OutputStreamWriter(new FileOutputStream(f),
					charset);
			try {
				writer.append(s);
			} finally {
				writer.close();
			}

			JDom j = new JDom(path, pathModified, m);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			Source xmlSource = new JDOMSource(j.Lift());
			Result outputTarget = new StreamResult(outputStream);
			TransformerFactory.newInstance().newTransformer()
					.transform(xmlSource, outputTarget);
			return new ByteArrayInputStream(outputStream.toByteArray());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}