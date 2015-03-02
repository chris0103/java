
package org.chirs.study.concurrency.flavors;

import java.util.concurrent.atomic.AtomicReference;

public class NakedThread implements NumberPrinter {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrinterName() {
		return "The naked thread printer";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int toNumber(int num) {
		AtomicReference<Integer> result = new AtomicReference<>();
		new Thread(
			() -> {
				int sum = 0;
				for (int i = 1; i <= num; i++) {
					System.out.print(i + "\t");
					sum += i;
				}
				System.out.println();
				result.compareAndSet(null, sum);
			}
		).start();
		while (result.get() == null);
		return result.get();
	}
}
