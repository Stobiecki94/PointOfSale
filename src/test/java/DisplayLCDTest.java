package test.java;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.devices.input.BarCodeDevice;
import main.devices.output.DisplayLCD;
import main.exception.InvalidBarCodeException;
import main.exception.ProductNotFoundException;
import main.model.BarCode;
import main.model.BaseOfProduct;
import main.model.Product;
import main.model.Receipt;


public class DisplayLCDTest {

	private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream printStream;
    static BarCodeDevice barCodeDevice;
    static DisplayLCD display;
    private static Receipt receipt;
    private static BaseOfProduct baseOfProduct;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		List<Product> baseOfProducts = new LinkedList<Product>();
        Product[] products = {
	            new Product(1, "Jajka", 10.0,new BarCode("11")),
	            new Product(2, "Mleko", 12.0,new BarCode("12")),
	            new Product(3, "Woda",5.5,  new BarCode("13")),
	            new Product(4, "Baton", 10.0,new BarCode("14")),
	            new Product(5, "Chipsy", 12.0,new BarCode("15")),
	            new Product(6, "Ryz",5.5,  new BarCode("16")),
	            new Product(7, "Sprite",3.2,  new BarCode("17"))
	    };
        for (int i = 0; i < products.length; i++) {
            baseOfProducts.add(products[i]);
        }
		barCodeDevice = new BarCodeDevice();
		display = new DisplayLCD();
		receipt = new Receipt();
		baseOfProduct = new BaseOfProduct(baseOfProducts);
	}
	
	@Before
	public void setUpBefore(){
		 printStream = System.out;
	        byteArrayOutputStream = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(byteArrayOutputStream));
		receipt=new Receipt();
	}
	
	@After
	public void setUpAfter(){
		System.setOut(printStream);
	}
	
	@Test (expected = InvalidBarCodeException.class)
	public void testShowErrorInvalidBarCode() throws InvalidBarCodeException, ProductNotFoundException {
		scan("");
	}
	
	@Test (expected = ProductNotFoundException.class)
	public void testShowErrorProductNotFound() throws InvalidBarCodeException, ProductNotFoundException {
		scan("88");
	}
	
	@Test
	public void testSumReceipt() throws InvalidBarCodeException, ProductNotFoundException {
		//when
		scan("11");
		scan("12");
		scan("13");
		scan("14");
		scan("15");
		scan("16");
		//then
		 assertThat(receipt.getSumPrice()).isEqualTo(55);
	}
	
	@Test
	public void testExitInput() throws InvalidBarCodeException, ProductNotFoundException {
		//when
		scan("11");
		scan("12");
		scan("exit");
		String printStreamExpected = new StringBuilder()
				.append("Jajka")
				.append(main.devices.output.MessageOutputDevice.TAB)
				.append(10.0)
				.append("\n")
	            .append("Mleko")
	            .append(main.devices.output.MessageOutputDevice.TAB)
	            .append(12.0)
	            .append("\n")
	            .append(main.devices.output.MessageOutputDevice.SUMA)
	            .append(main.devices.output.MessageOutputDevice.TAB)
	            .append(receipt.getSumPrice().toString())
	            .append("\n")
	            .toString();
		//then
		 assertEquals(byteArrayOutputStream.toString(),printStreamExpected);
	}
		
	private void scan(String inputBarCode) throws InvalidBarCodeException, ProductNotFoundException{
		BarCode barCode =barCodeDevice.readBarcode(inputBarCode);
		if(barCode.equals(main.system.PointOfSale.EXIT_CODE)){
			display.showSummary(receipt);
			return;
		}
		Product product = baseOfProduct.findProduct(barCode);
		receipt.addProduct(product);
		display.showProduct(product);
	}
}
