package org.chris.study.concurrency.flavors.actor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class PrintReqActor extends UntypedActor {

	private int retCount = 0;
	private Result result;
	private List<Integer> nums;
	
	public PrintReqActor(AtomicInteger sum, List<Integer> nums) {
		this.result = new Result(sum);
		this.nums = nums;
	}
	
	@Override
	public void onReceive(Object obj) throws Exception {
		if (obj instanceof Result) {
			retCount++;
			if (retCount == nums.size()) {
				getContext().system().shutdown();
			}
		} else {
			for (int num : nums) {
				ActorRef printer = getContext().actorOf(Props.create(PrintActor.class, () -> new PrintActor(result)), "printer-for-" + num);
				Message msg = new Message(num);
				printer.tell(msg, self());
			}
		}
	}
}
