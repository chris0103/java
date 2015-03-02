package org.chirs.study.concurrency.flavors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class Executor implements NumberPrinter {

	@Override
	public String getPrinterName() {
		return "The executor printer";
	}

	@Override
	public int toNumber(int num) {
		ExecutorCompletionService<Integer> service = new ExecutorCompletionService<Integer>(Executors.newFixedThreadPool(4));
		service.submit(
			() -> {
				int sum = 0;
				for (int i = 1; i <= num; i++) {
					System.out.print(i + "\t");
					sum += i;
				}
				System.out.println();
				return sum;
			}
		);
		try {
			return service.take().get();
		} catch (InterruptedException | ExecutionException e) {
			return 0;
		}
	}
}
