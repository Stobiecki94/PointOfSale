package main.devices.output;

import main.model.Product;
import main.model.Receipt;

public interface DisplayDevice {

	static String PRODUCT_NOT_FOUND = "PRODUCT NOT FOUND";
	static String INCORRECT_BARCODE = "INVALID BAR-CODE";
	
	void showSummary(Receipt receipt);
    void showProduct(Product product);
    void showMessageProductNotFound();
    void showMessageInvalidBarCode();
	
}
