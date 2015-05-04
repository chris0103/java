package org.chris.study.concurrency.future;

public class MySearcher implements ArchiveSearcher {

	@Override
	public String search(String target) {
		return target + " searched.";
	}

}
