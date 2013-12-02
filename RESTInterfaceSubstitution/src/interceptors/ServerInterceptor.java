/**
 * @author: Hao ZHANG
 * analyze the received message and change the root element.
 */

package interceptors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import org.apache.cxf.attachment.AttachmentDeserializer;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

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
		String theString = getStringFromInputStream(is);
		System.out.println(theString);
        message.setContent(InputStream.class, is);
        
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
 
}
