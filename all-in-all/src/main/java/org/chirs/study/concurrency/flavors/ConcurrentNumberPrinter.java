package org.chirs.study.concurrency.flavors;

import java.util.Arrays;
import java.util.List;

import org.chirs.study.concurrency.flavors.actor.Actor;

public class ConcurrentNumberPrinter {

	private NumberPrinter[] numPrinters = new NumberPrinter[] {
			// new NakedThread(), 
			// new Executor(), 
			// new ForkJoin(),
			new Actor(),
	};
	
	public void printNumber(List<Integer> numbers) throws InterruptedException {
		for (NumberPrinter numPrinter : numPrinters) {
			System.out.println(numPrinter.getPrinterName() + " is printing the numbers:");
			int sum = numPrinter.toNumber(numbers);
			System.out.println("The sum from [" + numPrinter.getPrinterName() + "] is " + sum + ".");
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		List<Integer> numbers = Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,}); 
		new ConcurrentNumberPrinter().printNumber(numbers);
	}
}
