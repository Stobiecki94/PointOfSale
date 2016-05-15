package main.model;

import main.exception.ProductNotFoundException;

public interface BaseOfProductInterface {

	public Product findProduct(BarCode barCode) throws ProductNotFoundException;	
}
