package org.chris.study.concurrency.flavors;

import java.util.List;

public interface NumberPrinter {

	/**
	 * Gets the print name.
	 * @return
	 */
	public String getPrinterName();
	
	/**
	 * Prints a list of numbers and returns the sum.
	 * @param num
	 */
	public int toNumber(List<Integer> nums);
}
