package lifting.conversion;

import java.io.InputStream;

import org.jdom2.Document;

/**
 * Interface to be implemented by classes which will convert a stream to an xml,
 * and vice and versa.
 * @author raphael
 *
 */
public interface StreamConversion {
	
	public Document stream2Doc(InputStream input);
	
	public InputStream doc2Stream(Document doc);

}
