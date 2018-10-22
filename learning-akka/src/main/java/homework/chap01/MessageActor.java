package homework.chap01;

import akka.actor.AbstractActor;
import akka.actor.Status;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;

public class MessageActor extends AbstractActor {

	private String message;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PartialFunction receive() {
		return ReceiveBuilder.match(String.class, str -> message = str)
				.matchAny(x -> sender().tell(new Status.Failure(new Exception("unknown message")), self())).build();
	}
	
	public String getMessage() {
		return message;
	}
}
