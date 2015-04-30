package org.chris.study.concurrency.flavors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

public class ForkJoin implements NumberPrinter {
	
	private AtomicInteger sum = new AtomicInteger();

	@Override
	public String getPrinterName() {
		return "The fork join printer";
	}

	@Override
	public int toNumber(List<Integer> nums) {
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(new ForkJoinAction(nums));
		return sum.get();
	}
	
	private class ForkJoinAction extends RecursiveAction {
		
		private List<Integer> nums;
		
		public ForkJoinAction(List<Integer> nums) {
			this.nums = nums;
		}
		
		@Override
		protected void compute() {
			if (nums.size() <= 5) {
				computerDirectly();
			} else {
				List<ForkJoinAction> actions = new ArrayList<ForkJoinAction>();
				int i = 0;
				for (; i + 5 < nums.size(); i = i + 5) {
					ForkJoinAction action  = new ForkJoinAction(nums.subList(i, i + 5));
					actions.add(action);
				}
				ForkJoinAction action  = new ForkJoinAction(nums.subList(i, nums.size()));
				actions.add(action);
				invokeAll(actions);
			}
		}
		
		private void computerDirectly() {
			for (int num : nums) {
				System.out.print(num + "\t");
				sum.addAndGet(num);
			}
		}
	}
}
