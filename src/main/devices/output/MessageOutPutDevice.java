package main.devices.output;

import main.model.Product;
import main.model.Receipt;

public abstract class MessageOutPutDevice {

		public static String TAB = "\t";
		public static String SUMA = "SUMA";
	    
	    
	    public String getProductMessage(Product product){
	        return new StringBuilder()
	                .append(product.getName())
	                .append(TAB)
	                .append(product.getPrice())
	                .toString();
	    }

	    public static String getTotalSumMessage(Receipt receipt){
	        return SUMA+TAB+receipt.getSumPrice().toString();
	    }
	  

}
