package org.chris.study.concurrency.jcip.examples.chap03;

import java.util.ArrayList;
import java.util.List;

/**
 * Listing 3.7. Implicitly allowing the <code>this</code> reference to escape. Don’t do this.
 */
public class ThisEscape {
	
	public ThisEscape(EventSource source) {
		source.registerListener(new EventListener() {

			@Override
			public void onEvent(Event e) {
				// do something...
			}
		});
	}
}

class EventSource {

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

interface EventListener {

	public void onEvent(Event e);
}

class Event {

}
