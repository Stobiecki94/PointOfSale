package test.java;

import org.junit.Test;
import org.junit.BeforeClass;

import main.exception.InvalidBarCodeException;
import main.devices.input.BarCodeDevice;
import main.model.BarCode;


public class BarCodeDeviceTest {
 
	private static BarCodeDevice barCodeDevice;

	@BeforeClass
	public static void setUp(){
		barCodeDevice=new BarCodeDevice();
	}

	
	@Test (expected = InvalidBarCodeException.class)
	public void shouldThrowException() throws InvalidBarCodeException{
			BarCode bareCode =barCodeDevice.readBarcode("");
	}
	
}
