package main.model;

public class Product {

	private int id;
	private String name;
	private double price;
	private BarCode barCode;
	
	public Product(int id, String name, double price, BarCode barCode){
		this.id = id;
		this.name=name;
		this.price=price;
		this.barCode=barCode;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}

	public double getPrice(){
	return price;
	}
	
	public void setPrice(int price){
		this.price=price;
	}
	
	public BarCode getBarCode(){
		return barCode;
	}
	
	public void setBarCode(BarCode barCode){
		this.barCode=barCode;
	}
}
