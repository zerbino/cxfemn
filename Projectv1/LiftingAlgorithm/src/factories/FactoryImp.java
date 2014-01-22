package factories;

import lifting.conversion.StreamConversion;
import lifting.conversion.StreamConversionImp;

public class FactoryImp implements Factory {

	@Override
	public StreamConversion createConverter() {
		return new StreamConversionImp();
	}

	

}
