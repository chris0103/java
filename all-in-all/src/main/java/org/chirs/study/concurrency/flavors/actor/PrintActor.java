package org.chirs.study.concurrency.flavors.actor;

import akka.actor.UntypedActor;

public class PrintActor extends UntypedActor {
	
	private Result result;
	
	public PrintActor(Result result) {
		this.result = result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceive(Object obj) throws Exception {
		if (obj instanceof Message) {
			Message message = (Message) obj;
			int num = message.getNum();
			System.out.print(num + "\t");
			result.getSum().addAndGet(num);
			getSender().tell(result, getSelf());
		} else {
			unhandled(obj);
		}
	}
}
