package org.chirs.study.concurrency.flavors;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import play.libs.ws.WS;


public class Executor implements SearchAgent {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAgentName() {
		return "The executor search agent";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String search(String key, List<String> engines) {
		ExecutorService threadPool = Executors.newFixedThreadPool(4);
		ExecutorCompletionService<String> service = new ExecutorCompletionService<String>(threadPool);
		for (String engine : engines) {
			String url = engine + key;
			service.submit(
				() -> {
					return WS.url(url).get().get(0).getBody();
				}
			);
		}
		try {
			return service.take().get();
		} catch (InterruptedException | ExecutionException e) {
			return null;
		} finally {
			threadPool.shutdown();
		}
	}
}
