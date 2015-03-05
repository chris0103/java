package org.chirs.study.concurrency.flavors;

import java.util.List;
import java.util.Optional;

import play.libs.ws.WS;

public class ForkJoin implements SearchAgent {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAgentName() {
		return "The Fork Join search agent";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String search(String key, List<String> engines) {
		Optional<String> result = engines.stream().parallel().map(
			(engine) -> {
				String url = engine + key;
				return WS.url(url).get().get(0).getBody();
			}
		).findAny();
		return result.get();
	}
}
