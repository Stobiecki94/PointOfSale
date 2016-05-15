package main.exception;

public class ProductNotFoundException extends Exception {

	private String detail;
	
	public ProductNotFoundException(String detail){
		this.detail=detail;
	}
	
	public String toString(){
		return "ProductNotFoundExceptions [" + detail + "]";
	}
}
