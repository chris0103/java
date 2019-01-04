package pong;

import static akka.pattern.Patterns.ask;
import static org.junit.Assert.assertEquals;
import static scala.compat.java8.FutureConverters.toJava;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import scala.concurrent.Future;

public class PongActorTest {

    ActorSystem system = ActorSystem.create();

    ActorRef actorRef = system.actorOf(Props.create(JavaPongActor.class));

    @Test
    public void shouldChainTogetherMultipleOperations() throws Exception {
        CompletionStage<String> cf = askPong("Ping").thenCompose(x -> askPong("Ping" + x)).handle((x, t) -> t != null ? "default" : x);
        assertEquals("default", get(cf));
    }

    @Test
    public void shouldCombineFutures() throws Exception {
        CompletionStage<String> cf = askPong("Ping").thenCombine(askPong("Ping"), (a, b) -> a + b);
        assertEquals("PongPong", get(cf));
    }

    @Test
    public void shouldEffectOnError() throws Exception {
        CompletionStage<String> cs = askPong("cause error").handle((x, t) -> {
            if (t != null) {
                System.out.println("Error: " + t);
            }
            return null;
        });
        assertEquals(null, get(cs));
    }

    @Test
    public void shouldPrintErrorToConsole() throws Exception {
        askPong("cause error").handle((x, t) -> {
            if (t != null) {
                System.out.println("Error: " + t);
            }
            return null;
        });
        Thread.sleep(100);
    }

    @Test
    public void shouldPrintToConsole() throws Exception {
        askPong("Ping").thenAccept(x -> System.out.println("replied with: " + x));
        Thread.sleep(100);
        // no assertion - just prints to console. Try to complete a CompletableFuture instead.
    }

    @Test
    public void shouldRecoverOnError() throws Exception {
        CompletionStage<String> cs = askPong("cause error").exceptionally(t -> {
            return "default";
        });
        assertEquals("default", get(cs));
    }

    @Test
    public void shouldRecoverOnErrorAsync() throws Exception {
        CompletionStage<String> cf = askPong("cause error")
                .handle((pong, ex) -> ex == null ? CompletableFuture.completedFuture(pong) : askPong("Ping")).thenCompose(x -> x);
        assertEquals("Pong", get(cf));
    }

    @Test
    public void shouldReplyToPingWithPong() throws Exception {
        final Future<?> sFuture = ask(actorRef, "Ping", 1000);

        @SuppressWarnings("unchecked")
        final CompletionStage<String> cs = (CompletionStage<String>) toJava(sFuture);

        assertEquals("Pong", get(cs));
    }

    @Test(expected = ExecutionException.class)
    public void shouldReplyToUnknownMessageWithFailure() throws Exception {
        Future<?> sFuture = ask(actorRef, "unknown", 1000);

        @SuppressWarnings("unchecked")
        final CompletionStage<String> cs = (CompletionStage<String>) toJava(sFuture);

        get(cs);
    }

    @Test
    public void shouldTransform() throws Exception {
        char result = (char) get(askPong("Ping").thenApply(x -> x.charAt(0)));
        assertEquals('P', result);
    }

    @Test
    public void shouldTransformAsync() throws Exception {
        CompletionStage<String> cs = askPong("Ping").thenCompose(x -> askPong("Ping"));
        assertEquals("Pong", get(cs));
    }

    private CompletionStage<String> askPong(String message) {
        Future<?> sFuture = ask(actorRef, message, 1000);

        @SuppressWarnings("unchecked")
        final CompletionStage<String> cs = (CompletionStage<String>) toJava(sFuture);

        return cs;
    }

    private Object get(CompletionStage<?> cs) throws Exception {
        @SuppressWarnings("unchecked")
        Object result = ((CompletableFuture<String>) cs).get(1000, TimeUnit.MILLISECONDS);

        return result;
    }
}
