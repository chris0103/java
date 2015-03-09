package org.chirs.study.concurrency.flavors.actor;

import akka.actor.UntypedActor;

public class PrintActor extends UntypedActor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceive(Object obj) throws Exception {
		if (obj instanceof Message) {
			Message message = (Message) obj;
			int num = message.getNum();
			System.out.println(num + "\t");
			message.getSum().addAndGet(num);
			getSender().tell(new Result(message.getSum()), getSelf());
		} else {
			unhandled(obj);
		}
	}

}
