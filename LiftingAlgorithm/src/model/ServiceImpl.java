package model;

import java.io.PrintWriter;
import java.io.File;

import org.apache.cxf.interceptor.InInterceptors;

import utile.UniformementRepresentable;

@InInterceptors(interceptors = {"interceptor.ClientRequestInterceptor"})
public class ServiceImpl implements Service {

	@Override
	public String op(Personne p) {
		PrintWriter writer;
		try {
			//path correspond au répertoire d'installation de tomcat
			String path = getClass().getClassLoader().getResource(".").getPath()+"FAKEDocuments/FAKEdatabase.txt";
			File f = new File(path);
			if(!f.exists()) {
				f.getParentFile().mkdirs();
			}
			writer = new PrintWriter(path, "UTF-8");
			writer.println("Incoming request for: ");
			writer.println(UniformementRepresentable.toString(new StringBuilder(), p));
			writer.close();
			System.out.println("Objet reçu: "+UniformementRepresentable.toString(new StringBuilder(), p));
			System.out.println("Chemin vers la base de donnée: ");
			System.out.println(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return p.getNom();
	}

}
