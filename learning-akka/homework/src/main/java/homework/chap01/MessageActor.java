package homework.chap01;

import akka.actor.AbstractActor;
import akka.actor.Status;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

public class MessageActor extends AbstractActor {

	private String message;

	@Override
	public PartialFunction<Object, BoxedUnit> receive() {
		return ReceiveBuilder.match(String.class, str -> message = str)
				.matchAny(x -> sender().tell(new Status.Failure(new Exception("unknown message")), self())).build();
	}
	
	public String getMessage() {
		return message;
	}
}
