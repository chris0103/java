package org.chirs.study.concurrency.flavors;

import java.util.Arrays;
import java.util.List;

public class ConcurrentSearchTask {

	private SearchAgent[] searchAgents = new SearchAgent[] {
		new NakedThread(), 
		// new Executor(), 
		// new ForkJoin(),
	};
	
	public void search(String key, List<String> engines) throws InterruptedException {
		for (SearchAgent agent : searchAgents) {
			System.out.println(agent.getAgentName() + " is search the keyword: " + key + "...");
			String result = agent.search(key, engines);
			System.out.println(result);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		String key = "StarWars";
		List<String> engines = Arrays.asList(
				new String[] {
				        "http://www.baidu.com/s?wd=", 
				        // "http://www.sogou.com/web?query=",
				        // "http://cn.bing.com/search?q=",
				        // "http://www.ask.com/web?q=", 
				        // "https://search.yahoo.com/search?p="
				}
		); 
		new ConcurrentSearchTask().search(key, engines);
	}
}
