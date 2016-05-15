package test.java;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import main.exception.ProductNotFoundException;
import main.model.BarCode;
import main.model.BaseOfProduct;
import main.model.Product;

public class BaseOfProductTest {
	private static BaseOfProduct baseOfProduct;
	private static Product firstProduct;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		List<Product> baseOfProducts = new LinkedList<Product>();
		
		firstProduct = mock(Product.class);
        when(firstProduct.getName()).thenReturn("First product");
        when(firstProduct.getPrice()).thenReturn(10.0);
        when(firstProduct.getBarCode()).thenReturn(new BarCode("11"));
        
        baseOfProducts.add(firstProduct);
        baseOfProduct = new BaseOfProduct(baseOfProducts);
	}

	@Test 
	public void testFindProduct() throws ProductNotFoundException{
		//when
        Product product = baseOfProduct.findProduct(new BarCode("11"));
        //then
        assertEquals(product,firstProduct);
	}

	@Test (expected = ProductNotFoundException.class)
	public void testThrowException() throws ProductNotFoundException{
		 Product product = baseOfProduct.findProduct(new BarCode("13"));
	}
}
