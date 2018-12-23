package homework.chap02;

import static akka.pattern.Patterns.ask;
import static scala.compat.java8.FutureConverters.toJava;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

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

    public CompletableFuture<String> reverseString(Object msg) {
        Future<?> future = ask(actor, msg, 2000);

        @SuppressWarnings("unchecked")
        CompletionStage<String> completionStage = (CompletionStage<String>) toJava(future);

        CompletableFuture<String> completableFuture = completionStage.toCompletableFuture();
        return completableFuture;
    }
}
