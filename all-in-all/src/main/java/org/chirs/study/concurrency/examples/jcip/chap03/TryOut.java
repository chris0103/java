package org.chirs.study.concurrency.examples.jcip.chap03;

public class TryOut {

	public static void main(String[] args) {
		EventSource secretHolder = new EventSource();
		ThisEscape escape = new ThisEscape(secretHolder);
		EventListener listener = secretHolder.getListeners().get(0);
		Event event = new Event();
		listener.onEvent(event);
		System.out.println("Ahha, the secret is disclosed: " + event.getEventMessage());
		
		SafeListener.newInstance(secretHolder);
		listener = secretHolder.getListeners().get(1);
		event = new Event();
		listener.onEvent(event);
		System.out.println("Ahha, the secret is disclosed: " + event.getEventMessage());		
	}
}
