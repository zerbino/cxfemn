//package serverLifter.archi;
//
//import java.io.InputStream;
//
//import org.jdom2.Document;
//
///**
// * An interface to be implemented by classes which will call lifting algorithm.
// * There are several implementations corresponding to cases : if the
// * lifterCaller is called when a response is sent to the client by the server,
// * or when the client is sending a request to the server.
// * 
// * @author raphael
// * 
// */
//public interface LifterCaller {
//
//	/**
//	 * Method to be used to call the lifting algorithm.
//	 * 
//	 * @return a document which can be converted into an InputStream to be
//	 *         injected into the context.
//	 */
//	public Document call();
//
//	/**
//	 * Method to be used to call the lifting algorithm.
//	 * 
//	 * @return an InputStream which can be directly injected into the context.
//	 */
//	public InputStream callStream();
//
//}
package serverLifter.archi;

import java.io.InputStream;

import org.jdom2.Document;

/**
 * An interface to be implemented by classes which will call lifting algorithm.
 * There are several implementations corresponding to cases : if the
 * lifterCaller is called when a response is sent to the client by the server,
 * or when the client is sending a request to the server.
 * 
 * @author raphael
 * 
 */
public interface LifterCaller {

	/**
	 * Method to be used to call the lifting algorithm.
	 * 
	 * @return a document which can be converted into an InputStream to be
	 *         injected into the context.
	 */
	public Document call();

	/**
	 * Method to be used to call the lifting algorithm.
	 * 
	 * @return an InputStream which can be directly injected into the context.
	 */
	public InputStream callStream();

}
