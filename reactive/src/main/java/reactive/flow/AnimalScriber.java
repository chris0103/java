package reactive.flow;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class AnimalScriber implements Subscriber<String> {

	private final long bufferSize;

	private Flow.Subscription subscription;
	private long count;

	public AnimalScriber(long bufferSize) {
		this.bufferSize = bufferSize;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		long initialRequestSize = bufferSize;
		count = bufferSize - bufferSize / 2; // re-request when half consumed
		(this.subscription = subscription).request(initialRequestSize);
	}

	@Override
	public void onNext(String item) {
		if (--count <= 0) {
			subscription.request(count = bufferSize - bufferSize / 2);
			System.out.println(item);
		}
	}

	@Override
	public void onError(Throwable e) {
		e.printStackTrace();
	}

	@Override
	public void onComplete() {

	}

}
