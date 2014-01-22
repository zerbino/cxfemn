package sandboxTest;
import java.util.ArrayList;

import model.Personne;

import com.sun.xml.internal.ws.wsdl.writer.document.Service;

import serverLifter.server.JDom;

public class Main{

	public static void main(String[] args) throws Exception {
		JDom j = new JDom("documents/NinoLabruti.xml", "documents/NinoLabrutiModifie.xml",Service.class.getMethod("op", Personne.class));
		j.Lift();
		
	}
}