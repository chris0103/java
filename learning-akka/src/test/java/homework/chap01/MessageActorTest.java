package homework.chap01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;

public class MessageActorTest {

	ActorSystem system = ActorSystem.create();
	
	@Test
	public void testOneMessage() {
		TestActorRef<MessageActor> testActor = TestActorRef.create(system, Props.create(MessageActor.class));
		testActor.tell("hello!", ActorRef.noSender());
		MessageActor actor = testActor.underlyingActor();
		assertEquals("hello!", actor.getMessage());
	}
}
