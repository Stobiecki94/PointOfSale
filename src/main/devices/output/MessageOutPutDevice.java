package main.devices.output;

import main.model.Product;
import main.model.Receipt;

public abstract class MessageOutputDevice {

		public static final String TAB = "\t";
		public static final String SUMA = "SUMA";
	    
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
