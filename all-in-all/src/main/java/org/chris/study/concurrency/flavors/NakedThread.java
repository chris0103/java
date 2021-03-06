
package org.chris.study.concurrency.flavors;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NakedThread implements NumberPrinter {
	
	private final Logger logger = LoggerFactory.getLogger(NakedThread.class);
	
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
		AtomicInteger sum = new AtomicInteger();
		for (int num : nums) {
			Thread thread = new Thread(
				() -> {
					System.out.print(num + "\t");
					sum.addAndGet(num);
				}
			);
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				logger.error("Failed to join thread.", e);
			}
		}
		return sum.get();
	}
}
