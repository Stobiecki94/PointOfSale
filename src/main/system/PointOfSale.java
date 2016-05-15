package main.system;

import main.devices.input.BarCodeDevice;
import main.devices.output.DisplayLCD;
import main.devices.output.Printer;
import main.exception.InvalidBarCodeException;
import main.exception.ProductNotFoundException;
import main.model.BarCode;
import main.model.BaseOfProduct;
import main.model.Product;
import main.model.Receipt;

public class PointOfSale {
	
	public static final String EXIT_CODE = "exit";
	
	private Receipt receipt;
	private DisplayLCD display;
	private Printer printer;
	private BarCodeDevice barCodeDevice;
	private BaseOfProduct baseOfProduct;
	
	public PointOfSale(DisplayLCD display, Printer printer, BarCodeDevice barCodeDevice, BaseOfProduct baseOfProduct){
		this.display=display;
		this.printer=printer;
		this.barCodeDevice=barCodeDevice;
		this.baseOfProduct=baseOfProduct;
		receipt = new Receipt();
	}
	
	public void checkOperation(String inputBarCode){
		try {
			scan(inputBarCode);
		}
		catch(InvalidBarCodeException e) {
			display.showMessageInvalidBarCode();
		}
		catch(ProductNotFoundException e) {
			display.showMessageProductNotFound();
		}
	}
	
	public void scan(String inputBarCode) throws InvalidBarCodeException, ProductNotFoundException{
		BarCode barCode =barCodeDevice.readBarcode(inputBarCode);
		if(barCode.equals(EXIT_CODE)){
			display.showSummary(receipt);
			printer.print(receipt);
			return;
		}
		Product product = baseOfProduct.findProduct(barCode);
		receipt.addProduct(product);
		display.showProduct(product);
	}
	
	public void nextTransaction(){
		receipt=new Receipt();
	}
}
