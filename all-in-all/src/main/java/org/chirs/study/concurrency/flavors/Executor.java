package org.chirs.study.concurrency.flavors;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Executor implements NumberPrinter {

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
		ExecutorCompletionService<Integer> service = new ExecutorCompletionService<Integer>(Executors.newFixedThreadPool(4));
		service.submit(
			() -> {
				int sum = 0;
				for (int num : nums) {
					System.out.print(num + "\t");
					sum += num;
				}
				System.out.println();
				return sum;
			}
		);
		try {
			return service.take().get();
		} catch (InterruptedException | ExecutionException e) {
			return 0;
		} finally {
		}
	}
}
