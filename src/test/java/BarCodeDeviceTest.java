package test.java;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import main.devices.input.BarCodeDevice;
import main.exception.InvalidBarCodeException;
import main.model.BarCode;

public class BarCodeDeviceTest {
 
	private static BarCodeDevice barCodeDevice;
	private static BarCode barCodeTest;

	@BeforeClass
	public static void setUpBeforeClass(){
		barCodeDevice=new BarCodeDevice();
		barCodeTest=new BarCode("11");
	}

	@Test 
	public void testEquals() throws InvalidBarCodeException{
			BarCode barCode =barCodeDevice.readBarcode("11");
			assertEquals(barCode.getBarCode(),barCodeTest.getBarCode());
	}
	
	@Test (expected = InvalidBarCodeException.class)
	public void testThrowException() throws InvalidBarCodeException{
			BarCode bareCode =barCodeDevice.readBarcode("");
	}
}
