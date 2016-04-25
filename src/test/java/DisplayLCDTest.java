package test.java;

import static org.fest.assertions.api.Assertions.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.exception.InvalidBarCodeException;
import main.exception.ProductNotFoundException;
import main.devices.input.BarCodeDevice;
import main.devices.output.DisplayLCD;
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
		barCodeDevice = new BarCodeDevice();
		display = new DisplayLCD();
		receipt = new Receipt();
		baseOfProduct = new BaseOfProduct(getProductTable());
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
	public void shouldShowErrorInvalidBarCode() throws InvalidBarCodeException, ProductNotFoundException {
		scan("");
	}
	
	@Test (expected = ProductNotFoundException.class)
	public void shouldShowErrorProductNotFound() throws InvalidBarCodeException, ProductNotFoundException {
		scan("88");
	}
	
	@Test
	public void checkReceipt() throws InvalidBarCodeException, ProductNotFoundException {
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
	public void checkExitInput() throws InvalidBarCodeException, ProductNotFoundException {
		//when
		scan("11");
		scan("12");
		scan("exit");
		 String printStreamExpected = new StringBuilder()
	                .append("Jajka")
	                .append(main.devices.output.MessageOutPutDevice.TAB)
	                .append(10.0)
	                .append("\n")
	                .append("Mleko")
	                .append(main.devices.output.MessageOutPutDevice.TAB)
	                .append(12.0)
	                .append("\n")

	                .append(main.devices.output.MessageOutPutDevice.SUMA+
	                		main.devices.output.MessageOutPutDevice.TAB+
	                		receipt.getSumPrice().toString())
	                .append("\n")
	                .toString();
		//then
		 assertThat(receipt.getSumPrice()).isEqualTo(22);
		 assertThat(byteArrayOutputStream.toString()).isEqualTo(printStreamExpected);
	}
	
	

	public void checkOperation(String inputBarCode){
		try{
			scan(inputBarCode);
		}
		catch(InvalidBarCodeException e)
		{
			display.showMessageInvalidBarCode();
		}
		catch(ProductNotFoundException e)
		{
			display.showMessageProductNotFound();
		}
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
	
	public static Map<Integer, Product> getProductTable(){
        Map<Integer, Product> baseOfProducts = new HashMap<>();
        Product[] products = {
	            new Product(1, "Jajka", 10.0,new BarCode("11")),
	            new Product(2, "Mleko", 12.0,new BarCode("12")),
	            new Product(3, "Woda",5.5,  new BarCode("13")),
	            new Product(4, "Baton", 10.0,new BarCode("14")),
	            new Product(5, "Cipsy", 12.0,new BarCode("15")),
	            new Product(6, "Ryz",5.5,  new BarCode("16")),
	            new Product(7, "Sprite",3.2,  new BarCode("17"))
	    };
        for (int i = 0; i < products.length; i++) {
            baseOfProducts.put((int) i, products[i]);
        }
        return baseOfProducts;
    }
	
}
