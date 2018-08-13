package reactive.flow;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.ForkJoinPool;

public class AdminPublisher implements Flow.Publisher<String> {

	private final ExecutorService executor = ForkJoinPool.commonPool(); // daemon-based

	private boolean subscribed; // true after first subscribe

	@Override
	public synchronized void subscribe(Subscriber<? super String> subscriber) {
		if (subscribed) {
			subscriber.onError(new IllegalStateException());
		} else {
			subscribed = true;
			subscriber.onSubscribe(new AnimnalPulisherSubscription(subscriber, executor));
		}
	}

}
