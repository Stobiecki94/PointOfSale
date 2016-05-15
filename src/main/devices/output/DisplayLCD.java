package main.devices.output;

import main.model.Product;
import main.model.Receipt;

public class DisplayLCD extends MessageOutputDevice implements DisplayDevice{

	@Override
	public void showSummary(Receipt receipt) {
		System.out.print(getTotalSumMessage(receipt)+"\n");
	}

	@Override
	public void showProduct(Product product) {
		System.out.print(getProductMessage(product)+"\n");
	}

	public void showMessageProductNotFound() {
		System.out.print(PRODUCT_NOT_FOUND+"\n");
	}

	public void showMessageInvalidBarCode() {
		System.out.println(INCORRECT_BARCODE);
	}
}
