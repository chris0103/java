package org.chris.study.concurrency.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class FutureDemo {

	private static Logger logger = LoggerFactory.getLogger(FutureDemo.class);
	
	private ExecutorService executor = Executors.newFixedThreadPool(10);
	private ArchiveSearcher searcher = new MySearcher();
	
	public void futureSearch(final String target) throws InterruptedException {
		Future<String> future = executor.submit(new Callable<String>() {
			
			@Override
			public String call() {
				return searcher.search(target);
			}}
		);
		// do other things while searching
		try {
			displayText(future.get()); // use future
		} catch (ExecutionException e) {
			logger.error("Failed to search archive: {}.", target, e);
		} finally {
			cleanup();
		}
	}
	
	public void futureTaskSearch(final String target) throws InterruptedException {
		FutureTask<String> future = new FutureTask<>(new Callable<String>() {
			
			@Override
			public String call() {
				return searcher.search(target);
			}}
		);
		executor.execute(future);
		try {
			displayText(future.get()); // use future
		} catch (ExecutionException e) {
			logger.error("Failed to search archive: {}.", target, e);
		} finally {
			cleanup();
		}
	}
	
	public void listenableFutureSearch(final String target) {
		ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
		ListenableFuture<String> result = service.submit(new Callable<String>() {

			public String call() throws Exception {
				return searcher.search(target);
			}
		});
	}
	
	private void displayText(String str) {
		System.out.println(str);
	}
	
	private void cleanup() {
		executor.shutdown();
	}
	
	public static void main(String[] args) throws Exception {
		FutureDemo demo = new FutureDemo();
		demo.futureSearch("Woopee!");
	}
}
