package interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import model.Personne;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import com.sun.xml.internal.ws.wsdl.writer.document.Service;

import serverLifter.old.Lifter;
//@Provider
/**
 * Unused
 * @author raphael
 *
 */
public class ClientRequestInterceptor extends AbstractPhaseInterceptor<Message>{

	public ClientRequestInterceptor() {
		super(Phase.UNMARSHAL );
	}

	public void handleMessage(Message message) throws Fault {
		
		

		boolean isOutbound = false;
		isOutbound = message == message.getExchange().getOutMessage()
				|| message == message.getExchange().getOutFaultMessage();

		if (!isOutbound)
		{
			try
			{
				Lifter l=new Lifter();
				InputStream is = message.getContent(InputStream.class);
				BufferedReader in = new BufferedReader(new InputStreamReader(is));
				String inputLine;
				String s="";
				while ((inputLine = in.readLine()) != null)
				    s+=inputLine;
				in.close();
				message.setContent(InputStream.class,l.HTTPAdapter(s,Service.class.getMethod("op", Personne.class)));
			}
			catch(IOException e){
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

		}
		
		
	}
}