package org.chirs.study.concurrency.examples.jcip.chap03;

/**
 * Listing 3.7. Implicitly allowing the this reference to escape. Don’t do this.
 */
public class ThisEscape {
	
	private String secret = "I have a secret that I will never tell others...";

	public ThisEscape(EventSource source) {
		source.registerListener(new EventListener() {

			@Override
			public void onEvent(Event e) {
				e.setEventMessage(ThisEscape.this.secret);
			}
			
		});
	}
}
