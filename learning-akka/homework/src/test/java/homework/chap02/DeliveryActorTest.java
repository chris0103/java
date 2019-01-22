package homework.chap02;

import static akka.pattern.Patterns.ask;
import static org.junit.Assert.assertEquals;
import static scala.compat.java8.FutureConverters.toJava;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import scala.concurrent.Future;

public class DeliveryActorTest {

    private ActorSystem system = ActorSystem.create();

    private ActorRef actorRef = system.actorOf(Props.create(DeliveryActor.class));

    @Test
    public void testPingPong() throws Exception {
        CompletionStage<String> cs = askDelivery("Ping");
        assertEquals("Pong", get(cs));
    }

    @Test
    public void testReverse() throws Exception {
        CompletionStage<String> cs = askDelivery("Hello");
        assertEquals("olleH", get(cs));
    }
    
    @Test
    public void testUnknownMessage() throws Exception {
        CompletionStage<String> cs = askDelivery(new Object()).exceptionally(t -> t.getMessage());
        assertEquals("unknown message", get(cs));
    }

    private CompletionStage<String> askDelivery(Object message) {
        Future<?> sFuture = ask(actorRef, message, 10000);

        @SuppressWarnings("unchecked")
        final CompletionStage<String> cs = (CompletionStage<String>) toJava(sFuture);

        return cs;
    }

    private Object get(CompletionStage<String> cs) throws Exception {
        return ((CompletableFuture<String>) cs).get(1000, TimeUnit.MILLISECONDS);
    }
}
