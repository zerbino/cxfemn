/**
 * @author: Hao ZHANG
 * analyze the received message and change the root element.
 */

package interceptors;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import org.apache.cxf.attachment.AttachmentDeserializer;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class ServerInterceptor extends AbstractPhaseInterceptor<Message> {

	public ServerInterceptor(String phase) {
		super(phase);
	}
	
	public ServerInterceptor() {
		this(Phase.RECEIVE);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		System.out.println("zh adds "+this.getPhase());
		String contentType = (String) message.get(Message.CONTENT_TYPE);
        if (contentType != null && contentType.toLowerCase().indexOf("multipart/related") != -1) {
            AttachmentDeserializer ad = new AttachmentDeserializer(message);
            try {
                ad.initializeAttachments();
                System.out.println(ad.toString());
            } catch (IOException e) {
                throw new Fault(e);
            }
        }
        for (String k: message.keySet()){
        	System.out.println(k + " : "+message.get(k));
        }
	}
}
