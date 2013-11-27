package utile;

import java.io.IOException;

import org.apache.cxf.attachment.AttachmentDeserializer;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class MyInterceptor extends AbstractPhaseInterceptor<Message> {
	   public MyInterceptor() {
	        super(Phase.RECEIVE);
	    }
	 
	    public void handleMessage(Message message) {
	        String contentType = (String) message.get(Message.CONTENT_TYPE);
	        if (contentType != null && contentType.toLowerCase().indexOf("multipart/related") != -1) {
	            AttachmentDeserializer ad = new AttachmentDeserializer(message);
	            try {
	                ad.initializeAttachments();
	                System.out.println("intercepteur appelé");
	            } catch (IOException e) {
	                throw new Fault(e);
	            }
	        }
	    }
	 
	    public void handleFault(Message messageParam) {
	    }
	}