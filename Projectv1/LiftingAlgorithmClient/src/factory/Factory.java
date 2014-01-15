package factory;

import lifting.conversion.StreamConversion;
import lifting.old.ClientLifterCaller;

public interface Factory {
	
	public StreamConversion createConverter();
	
	public ClientLifterCaller createClientLifterCaller();

}
