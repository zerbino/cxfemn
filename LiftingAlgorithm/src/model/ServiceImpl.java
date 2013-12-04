package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.jws.WebService;

import utile.UniformementRepresentable;


public class ServiceImpl implements Service {

	@Override
	public String op(Personne p) {
		PrintWriter writer;
		try {
			String path = getClass().getClassLoader().getResource(".").getPath()+"/documents/database.txt";
			File f = new File(path);
			if(!f.exists()) {
				f.getParentFile().mkdirs();
			}
			writer = new PrintWriter(path, "UTF-8");
			writer.println("Incoming request for: ");
			writer.println(UniformementRepresentable.toString(new StringBuilder(), p));
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p.getNom();
	}

}
