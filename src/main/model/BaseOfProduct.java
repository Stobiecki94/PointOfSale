package main.model;

import java.util.List;

import main.exception.ProductNotFoundException;

public class BaseOfProduct implements main.model.BaseOfProductInterface {

	List<Product> listOfProducts;
	    public BaseOfProduct(List<Product> hashMapOfProduct) {
	        this.listOfProducts = hashMapOfProduct;
	    }
	    
	@Override
	public Product findProduct(BarCode barCode) throws ProductNotFoundException {
		Product foundProduct = null;
        for(Product product : listOfProducts) {
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
