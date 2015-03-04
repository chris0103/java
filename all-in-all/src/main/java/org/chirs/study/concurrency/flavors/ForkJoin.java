package org.chirs.study.concurrency.flavors;

import java.util.List;

public class ForkJoin implements NumberPrinter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrinterName() {
		return "The Fork Join printer";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int toNumber(List<Integer> nums) {
		int sum = nums.stream().parallel().mapToInt(
			(num) -> {
				System.out.print(num + "\t");
				return num;
			}
		).sum();
		System.out.println();
		return sum;
	}
}
