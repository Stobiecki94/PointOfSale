package main.devices.input;

import main.exception.InvalidBarCodeException;
import main.model.BarCode;

public class BarCodeDevice implements BarCodeScanner{

	public BarCodeDevice(){}
	
	@Override
	public BarCode readBarcode(String inputBarCode) throws InvalidBarCodeException {
		if(inputBarCode != null && !inputBarCode.isEmpty())
			return new BarCode(inputBarCode);
		else{
			throw new InvalidBarCodeException(inputBarCode);
		}	
	}
}
