package homework.chap02;

import static akka.pattern.Patterns.ask;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import scala.concurrent.Future;

public class StringReverseService {

	private ActorSystem system;
	
	private ActorRef actor;
	
	public StringReverseService() {
		system = ActorSystem.create();
		actor = system.actorOf(Props.create(StringReverseActor.class));
	}
	
	public Future<?> reverseString(String str) {
		return ask(actor, str, 2000);
	}
}
