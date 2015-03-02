package org.chirs.study.concurrency.flavors;

public class ConcurrentNumberPrinter {

	private NumberPrinter[] numPrinters = new NumberPrinter[] {
			new NakedThread(), new Executor(),
	};
	
	public void printNumber(int num) throws InterruptedException {
		for (NumberPrinter numPrinter : numPrinters) {
			System.out.println(numPrinter.getPrinterName() + " is printing the numbers:");
			int sum = numPrinter.toNumber(num);
			System.out.println("The sum from [" + numPrinter.getPrinterName() + "] is " + sum + ".");
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new ConcurrentNumberPrinter().printNumber(10);
	}
}
