package factory;

import lifting.conversion.StreamConversion;
import lifting.conversion.StreamConversionImp;
import lifting.old.ClientLifterCaller;

public class FactoryImp implements Factory {

	@Override
	public StreamConversion createConverter() {
		return new StreamConversionImp();
	}

	@Override
	public ClientLifterCaller createClientLifterCaller() {
		// TODO Auto-generated method stub
		return null;
	}

}
