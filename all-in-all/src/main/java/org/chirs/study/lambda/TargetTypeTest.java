package org.chirs.study.lambda;

import java.util.concurrent.Callable;

public class TargetTypeTest {

	public void invoke(Runnable r) {
		System.out.println("study.lambda.TargetTypeTest.invoke(Runnable) invoked.");
	    r.run();
	}
	
	public <T> T invoke(Callable<T> c) throws Exception {
		System.out.println("study.lambda.TargetTypeTest.invoke(Callable<T>) invoked.");
	    return c.call();
	}
	
	public static void main(String[] args) throws Exception {
		TargetTypeTest test = new TargetTypeTest();
		String s = test.invoke(() -> "done");
		System.out.println(s);
	}
}
