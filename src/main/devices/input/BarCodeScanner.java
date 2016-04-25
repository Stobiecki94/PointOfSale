package main.devices.input;

import main.exception.InvalidBarCodeException;
import main.model.BarCode;


public interface BarCodeScanner {

	 public BarCode readBarcode(String inputBarCode) throws InvalidBarCodeException;
	
}
