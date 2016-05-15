package test.java;

import java.util.LinkedList;
import java.util.List;

import main.devices.input.BarCodeDevice;
import main.devices.output.DisplayLCD;
import main.devices.output.Printer;
import main.model.BarCode;
import main.model.BaseOfProduct;
import main.model.Product;
import main.system.PointOfSale;

public class logicTest {
	
	 static DisplayLCD display = new DisplayLCD();
	 static Printer printer = new Printer();
	 static BarCodeDevice barCodeDevice = new BarCodeDevice();
	 static PointOfSale pointOfSale;
	 static BaseOfProduct baseOfProduct;
	 
	public static void main(String[] args) {
		baseOfProduct = new BaseOfProduct(getProductTable());
		pointOfSale = new PointOfSale(display,printer, barCodeDevice,  baseOfProduct);
		showBaseOfProducts();
		testWorking();
	}

	  public static List<Product> getProductTable(){
		  List<Product> baseOfProducts = new LinkedList<Product>();
	        Product[] products = {
		            new Product(1, "Jajka", 10.0,new BarCode("11")),
		            new Product(2, "Mleko", 12.0,new BarCode("12")),
		            new Product(3, "Woda",5.5,  new BarCode("13")),
		            new Product(4, "Baton", 10.0,new BarCode("14")),
		            new Product(5, "Chipsy", 12.0,new BarCode("15")),
		            new Product(6, "Ryz",5.5,  new BarCode("16")),
		            new Product(7, "Sprite",3.2,  new BarCode("17"))
		    };
	        for (int i = 0; i < products.length; i++) {
	            baseOfProducts.add(products[i]);
	        }
	        return baseOfProducts;
	    }
	  
	  public static void showBaseOfProducts(){
		  System.out.println("BAZA PRODUKTÓW:");
			for(Product product : getProductTable()) {
	            System.out.println(product.getName() + "\t\t" + product.getPrice());
	        }
	  }
	  
	  public static void testWorking(){
		  System.out.println("\nROZPOCZECIE SKANOWANIA");
			try {
			pointOfSale.checkOperation("11");
				Thread.sleep(1500);
			pointOfSale.checkOperation("17");
				Thread.sleep(1500);
			pointOfSale.checkOperation("");
				Thread.sleep(1500);
				pointOfSale.checkOperation("15");
				Thread.sleep(1500);
			pointOfSale.checkOperation("22");
				Thread.sleep(1500);
			pointOfSale.checkOperation("15");
				Thread.sleep(1500);
			pointOfSale.checkOperation("exit");
			} catch (InterruptedException e) {
			}	
	  } 
}
