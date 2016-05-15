package main.devices.output;

import main.model.Product;
import main.model.Receipt;

public class Printer extends MessageOutputDevice implements PrinterDevice {

	public static final String HEADER = "--RECEIPT--\nNAME" + TAB + "PRICE\n";
	
	@Override
	public void print(Receipt receipt) {
	
		System.out.print(HEADER);
		for(Product product : receipt.getAll())
		{
			System.out.print(this.getProductMessage(product)+"\n");
		}
		System.out.print("\n"+this.getTotalSumMessage(receipt));
	}
}
