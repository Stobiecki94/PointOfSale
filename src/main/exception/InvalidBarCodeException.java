package main.exception;

public class InvalidBarCodeException extends Exception {

	private String detail;
	
	public InvalidBarCodeException(String detail){
		this.detail=detail;
	}
	
	public String toString(){
		return "Invalid bar-code ["+detail+"]";
	}
	
}
