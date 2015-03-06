
package org.chirs.study.concurrency.flavors;

import java.util.List;
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
	public int toNumber(List<Integer> nums) {
		AtomicReference<Integer> result = new AtomicReference<>();
		new Thread(
			() -> {
				int sum = 0;
				for (int num : nums) {
					System.out.print(num + "\t");
					sum += num;
				}
				System.out.println();
				result.compareAndSet(null, sum);
			}
		).start();
		while (result.get() == null);
		return result.get();
	}
}
