package org.chirs.study.concurrency.flavors.actor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.chirs.study.concurrency.flavors.NumberPrinter;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class Actor extends UntypedActor implements NumberPrinter {

	@Override
	public String getPrinterName() {
		return "The print actor";
	}

	@Override
	public int toNumber(List<Integer> nums) {
		ActorSystem system = ActorSystem.create("print");
		AtomicInteger sum = new AtomicInteger();
		for (int num : nums) {
			final ActorRef actor = system.actorOf(Props.create(
				() -> this
			), "master");
		}
		return 0;
		
		// Props.create((UntypedActorFactory) () -> new Querier(question, engines, result));
	}

	@Override
	public void onReceive(Object obj) throws Exception {
		if (obj instanceof Result) {
			
		}
		
	}
}
