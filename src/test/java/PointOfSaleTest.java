package test.java;

import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import main.devices.input.BarCodeDevice;
import main.exception.InvalidBarCodeException;
import main.exception.ProductNotFoundException;
import main.model.BarCode;
import main.model.BaseOfProduct;
import main.model.Product;
import main.system.PointOfSale;

public class PointOfSaleTest {

    static BarCodeDevice barCodeDevice;
    private static BaseOfProduct baseOfProduct;
    private static PointOfSale pointOfSale;

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
		baseOfProduct = new BaseOfProduct(baseOfProducts);
		pointOfSale = new PointOfSale(null, null, barCodeDevice,  baseOfProduct);
	}

	@Test (expected = InvalidBarCodeException.class)
	public void testScanThrowInvalidBarException() throws InvalidBarCodeException, ProductNotFoundException {
		pointOfSale.scan("");
	}
	
	@Test (expected = ProductNotFoundException.class)
	public void testThrowProductNotFoundException() throws InvalidBarCodeException, ProductNotFoundException{
		pointOfSale.scan("22");
	}
}
