package main.model;

import java.util.ArrayList;
import java.util.List;


public class Receipt implements ReceiptInterface {

	private List<Product> products = new ArrayList<Product>();
	private Double sum=0.0;
	
	@Override
	public Double getSumPrice() {
		return sum;
	}

	@Override
	public List<Product> getAll() {
		return products;
	}

	@Override
	public void addProduct(Product product) {
		products.add(product);
		sum+=product.getPrice();
	}

	
}
