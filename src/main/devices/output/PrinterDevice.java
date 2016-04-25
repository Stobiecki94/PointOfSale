package main.devices.output;

import main.model.Receipt;

public interface PrinterDevice {

    void print(Receipt receipt);
	
}
