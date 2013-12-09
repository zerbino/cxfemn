package sandboxTest;
import serverLifter.JDom;

public class Main{

	public static void main(String[] args) throws Exception {
		JDom j = new JDom("documents/NinoLabruti.xml", "documents/NinoLabrutiModifie.xml");
		j.Lift();
	}
}