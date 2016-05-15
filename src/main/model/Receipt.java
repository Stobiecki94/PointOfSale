package main.model;

import java.util.LinkedList;
import java.util.List;

public class Receipt implements ReceiptInterface {

	private List<Product> products = new LinkedList<Product>();
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
