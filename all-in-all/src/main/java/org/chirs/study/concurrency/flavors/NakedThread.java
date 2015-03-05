package org.chirs.study.concurrency.flavors;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class NakedThread extends AbstractSearchAgent implements SearchAgent {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAgentName() {
		return "The naked thread search agent";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String search(String key, List<String> engines) {
		AtomicReference<String> result = new AtomicReference<String>();
		for (String engine : engines) {
			String url = engine + key;
			new Thread(
				() -> {
					result.compareAndSet(null, wsClient.url(url).get().get(1000).getBody());
				}
			).start();
		}
		while (result.get() == null);
		return result.get();
	}
}
