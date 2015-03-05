package org.chirs.study.concurrency.flavors;

import java.util.List;

public interface SearchAgent {

	/**
	 * Gets the agent name.
	 * @return
	 */
	public String getAgentName();
	
	/**
	 * Returns the search result of in given key in supplied search engines.
	 * @param key
	 * @param engines
	 * @return
	 */
	public String search(String key, List<String> engines);
}
