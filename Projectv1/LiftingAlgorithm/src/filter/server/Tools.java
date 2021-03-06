package filter.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;




public class Tools {
	
	private static String inputStreamToStringExcept(InputStream input) throws IOException{
		BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
		String toRet="";
		String tmp = buffer.readLine();
		while(tmp!=null){
			toRet+=tmp;
			tmp=buffer.readLine();
		}
		buffer.close();
		return toRet;
		
	}
	
	public static String inputStreamToString(InputStream in){
		try {
			return inputStreamToStringExcept(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error at"+Tools.class;
	}
	
	public static Class<?>[] toClassTab(Type[] types){
		Class<?>[] toRet  =new Class<?>[types.length];
		for(int i=0;i<types.length;i++){
			toRet[i]=(Class<?>)types[i];
		}
		return toRet;
	}
	
	

}
