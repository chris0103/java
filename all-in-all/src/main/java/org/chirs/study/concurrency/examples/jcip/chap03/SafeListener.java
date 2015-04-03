package org.chirs.study.concurrency.examples.jcip.chap03;

/**
 * Listing 3.8. Using a factory method to prevent the this reference from escaping during construction.
 */
public class SafeListener {
	
	private final EventListener listener;
	
	private String secret = "I have a safe secret that I will never tell others...";
	
	private SafeListener() {
		listener = new EventListener() {
			
			public void onEvent(Event e) {
				e.setEventMessage(SafeListener.this.secret);
			}
		};
	}
	
	public static SafeListener newInstance(EventSource source) {
		SafeListener safe = new SafeListener();
		source.registerListener(safe.listener);
		return safe;
	}
}
