package org.chirs.study.concurrency.flavors;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Executor implements NumberPrinter {
	
	private final Logger logger = LoggerFactory.getLogger(Executor.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrinterName() {
		return "The executor printer";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int toNumber(List<Integer> nums) {
		AtomicInteger sum = new AtomicInteger();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for (int num : nums) {
			executor.submit(
				() -> {
					System.out.print(num + "\t");
					sum.addAndGet(num);
				}
			);
		}
		try {
			executor.shutdown();
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			logger.error("Failed to await executor termination.", e);
		}
		System.out.println();
		return sum.get();
	}
}
