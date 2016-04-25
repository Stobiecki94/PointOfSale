package test.java;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.BeforeClass;
import java.util.HashMap;
import java.util.Map;


import main.exception.ProductNotFoundException;
import main.model.BarCode;
import main.model.BaseOfProduct;
import main.model.Product;

public class BaseOfProductTest {
	private static BaseOfProduct baseOfProduct;
	private static Product firstProduct;
	
	@BeforeClass
	public static void setUp(){
		Map<Integer, Product> baseOfProducts = new HashMap<>();
		firstProduct = mock(Product.class);
        when(firstProduct.getName()).thenReturn("First product");
        when(firstProduct.getPrice()).thenReturn(10.0);
        when(firstProduct.getBarCode()).thenReturn(new BarCode("11"));
        baseOfProducts.put(1, firstProduct);
        baseOfProduct = new BaseOfProduct(baseOfProducts);
	}

	@Test 
	public void shouldFindProduct() throws ProductNotFoundException{
        Product product = baseOfProduct.findProduct(new BarCode("11"));
        assertThat(product).isEqualTo(firstProduct);
	}
	
	
	
	@Test (expected = ProductNotFoundException.class)
	public void shouldThrowException() throws ProductNotFoundException{
		 Product product = baseOfProduct.findProduct(new BarCode("13"));
	}
}
