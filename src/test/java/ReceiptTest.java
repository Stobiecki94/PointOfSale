package test.java;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.model.Product;
import main.model.Receipt;

public class ReceiptTest {
  
	private static Product product1, product2, product3, product4;
    private static Receipt receipt;

    @BeforeClass
    public static void setUpBeforeClass() {
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
    
    @Before
    public void setUpBefore(){
    	receipt=new Receipt();
    }

    @Test
    public void testAddProduct() {
        //when
        receipt.addProduct(product1);
        //then
        assertEquals(receipt.getAll().get(0),product1);
        assertThat(receipt.getSumPrice()).isEqualTo(2.00);
    }
    
    @Test
    public void testAddProducts() {
        //when
        receipt.addProduct(product1);
        receipt.addProduct(product2);
        receipt.addProduct(product3);
        receipt.addProduct(product4);
        //then
        assertThat(receipt.getAll().size()).isEqualTo(4);
    }

    @Test
    public void testAddAllPrices() {
        //when
        receipt.addProduct(product1);
        receipt.addProduct(product2);
        receipt.addProduct(product3);
        receipt.addProduct(product4);
        //then
        assertThat(receipt.getSumPrice()).isEqualTo(29.95);
    }
}
