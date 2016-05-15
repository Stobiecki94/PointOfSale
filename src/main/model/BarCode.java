package main.model;

public class BarCode {

	private String code=null;
	
	public BarCode(String code){
		this.code=code;	
	}
	
	public String getBarCode(){
		return code;
	}
	
	public void setBarCode(String code){
		this.code=code;
	}
	
	public String toString(){
		return getBarCode();
	}

	public boolean equals(BarCode barCode){
		return code==barCode.getBarCode();
	}
	
	public boolean equals(String barCode){
		return code==barCode;
	}
}
