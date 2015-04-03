package org.chirs.study.concurrency.examples.jcip.chap03;

import java.util.ArrayList;
import java.util.List;

public class EventSource {

	private List<EventListener> listeners = new ArrayList<EventListener>();

	public void registerListener(EventListener listener) {
		if (listener != null) {
			listeners.add(listener);
		}
	}
	
	public List<EventListener> getListeners() {
		return listeners;
	}
}
