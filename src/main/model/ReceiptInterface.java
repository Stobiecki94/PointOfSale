package main.model;

import java.util.List;

public interface ReceiptInterface {
	
	Double getSumPrice();
	
	List<Product> getAll();
	
	void addProduct(Product product);
	
}
