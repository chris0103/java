package org.chris.study.concurrency.jcip.examples.chap03;

/**
 * Listing 3.8. Using a factory method to prevent the <code>this</code> reference from escaping during construction.
 */
public class SafeListener {
	
	private final EventListener listener;
	
	private SafeListener() {
		listener = new EventListener() {
			
			public void onEvent(Event e) {
				// do something...
			}
		};
	}
	
	public static SafeListener newInstance(EventSource source) {
		SafeListener safe = new SafeListener();
		source.registerListener(safe.listener);
		return safe;
	}
}
