package reactive.flow;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.Future;

public class AnimnalPulisherSubscription implements Subscription {

	private final Flow.Subscriber<? super String> subscriber;
	private final ExecutorService executor;

	private Future<?> future; // to allow cancellation
	private List<Animal> animals;
	private int animalsSent;

	public AnimnalPulisherSubscription(Flow.Subscriber<? super String> subscriber, ExecutorService executor) {
		this.subscriber = subscriber;
		this.executor = executor;
		this.animalsSent = 0;
	}

	@Override
	public synchronized void request(long n) {
		long animalsToSend = n;
		if (animalsSent + animalsToSend > animals.size()) {
			animalsToSend = animals.size() - animalsSent;
		}

		long finalAnimalsToSend = animalsToSend;
		future = executor.submit(() -> {

			animals.stream().map(Animal::getName).filter(name -> name.startsWith("C")).skip(animalsSent)
					.limit(finalAnimalsToSend).forEach(subscriber::onNext);

			animalsSent += finalAnimalsToSend;
			if (animalsSent == animals.size()) {
				subscriber.onComplete();
			}
		});
	}

	@Override
	public synchronized void cancel() {
		if (future != null) {
			future.cancel(false);
		}
	}

}
