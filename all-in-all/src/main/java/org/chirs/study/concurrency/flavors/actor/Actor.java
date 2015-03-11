package org.chirs.study.concurrency.flavors.actor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.chirs.study.concurrency.flavors.NumberPrinter;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Actor implements NumberPrinter {

	@Override
	public String getPrinterName() {
		return "The actor printer";
	}

	@Override
	public int toNumber(List<Integer> nums) {
		AtomicInteger sum = new AtomicInteger();
		ActorSystem system = ActorSystem.create("print");
		final ActorRef actor = system.actorOf(Props.create(PrintReqActor.class, () -> new PrintReqActor(sum, nums)), "master");
		actor.tell(new Object(), ActorRef.noSender());
		while (!system.isTerminated());
		return sum.get();
	}
}
