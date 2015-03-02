package org.chirs.study.concurrency.flavors;

public interface NumberPrinter {

	/**
	 * Gets the print name.
	 * @return
	 */
	public String getPrinterName();
	
	/**
	 * Prints from 1 to the given number and returns the sum.
	 * @param num
	 */
	public int toNumber(int num);
}
