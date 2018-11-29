package homework.chap02;

import akka.actor.AbstractActor;
import akka.actor.Status;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

public class StringReverseActor extends AbstractActor {

	@Override
	public PartialFunction<Object, BoxedUnit> receive() {
		return ReceiveBuilder.match(String.class, str -> sender().tell(reverse(str), self()))
				.matchAny(x -> sender().tell(new Status.Failure(new Exception("unknown message")), self())).build();
	}

	private String reverse(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		} else {
			StringBuffer sb = new StringBuffer(str);
			return sb.reverse().toString();
		}
	}
}
