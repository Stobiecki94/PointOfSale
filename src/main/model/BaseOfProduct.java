package main.model;

import java.util.HashMap;
import java.util.Map;

import main.exception.ProductNotFoundException;

public class BaseOfProduct implements main.model.BaseOfProductInterface {

	
	private Map<Integer, Product> hashMapOfProduct = new HashMap<Integer,Product>();

	    public BaseOfProduct(Map<Integer, Product> hashMapOfProduct) {
	        this.hashMapOfProduct = hashMapOfProduct;
	    }
	
	
	@Override
	public Product findProduct(BarCode barCode) throws ProductNotFoundException {
		
		Product foundProduct = null;
		
        for(Product product : hashMapOfProduct.values()) {
            if (product.getBarCode().equals(barCode)) {
                foundProduct = product;
                break;
            }
        }
        if (foundProduct == null) {
        	throw new ProductNotFoundException("Product barcode="+barCode+" not found");
        }
		return foundProduct;
        
       
	}
}
