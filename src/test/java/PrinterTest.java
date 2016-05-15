package test.java;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.devices.input.BarCodeDevice;
import main.devices.output.Printer;
import main.model.Product;
import main.model.Receipt;

public class PrinterTest {

	private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream printStream;
    static BarCodeDevice barCodeDevice;
	private static Printer printer;
	private static Receipt receipt;
	private static Product product1, product2, product3, product4;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		printer=new Printer();
		receipt=new Receipt();
		
		product1 = mock(Product.class);
        when(product1.getPrice()).thenReturn(2.00);
        when(product1.getName()).thenReturn("Mleko");
        
        product2 = mock(Product.class);
        when(product2.getPrice()).thenReturn(5.45);
        when(product2.getName()).thenReturn("Napoj");
        
        product3 = mock(Product.class);
        when(product3.getPrice()).thenReturn(22.00);
        when(product3.getName()).thenReturn("Kawior");
        
        product4 = mock(Product.class);
        when(product4.getPrice()).thenReturn(0.50);
        when(product4.getName()).thenReturn("Lizak");
	}
	
	@After
	public void setUpAfter(){
		System.setOut(printStream);
		receipt=new Receipt();
	}
	
	@Before
	public void setUpBefore(){
		 printStream = System.out;
	     byteArrayOutputStream = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(byteArrayOutputStream));
		receipt=new Receipt();
	}
	
	@Test
	public void testPrint() {
		//when
		receipt.addProduct(product1);
        receipt.addProduct(product2);
        receipt.addProduct(product3);
        printer.print(receipt);
        String printStreamExpected = new StringBuilder()
                .append(main.devices.output.Printer.HEADER)
                .append("Mleko")
                .append(main.devices.output.MessageOutputDevice.TAB)
                .append(2.0)
                .append("\n")
                .append("Napoj")
                .append(main.devices.output.MessageOutputDevice.TAB)
                .append(5.45)
                .append("\n")
                .append("Kawior")
                .append(main.devices.output.MessageOutputDevice.TAB)
                .append(22.0)
                .append("\n\n")
                .append(main.devices.output.MessageOutputDevice.SUMA)
                .append(main.devices.output.MessageOutputDevice.TAB)
                .append(receipt.getSumPrice().toString())
                .toString();
        //then
        assertEquals(byteArrayOutputStream.toString(),printStreamExpected);
	}
	
	@Test
	public void testSumReceipt() {
		//when
		receipt.addProduct(product1);
        receipt.addProduct(product2);
        receipt.addProduct(product3);
        receipt.addProduct(product4);
        //then
        assertThat(receipt.getSumPrice()).isEqualTo(29.95);
	}
}
