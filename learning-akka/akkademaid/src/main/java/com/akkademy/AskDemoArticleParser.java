package com.akkademy;

import static akka.pattern.Patterns.ask;
import static scala.compat.java8.FutureConverters.toJava;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import com.akkademy.messages.GetRequest;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;
import akka.util.Timeout;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

public class AskDemoArticleParser extends AbstractActor {

    private final ActorSelection artcileParseActor;

    private final ActorSelection cacheActor;

    private final ActorSelection httpClientActor;

    private final Timeout timeout;

    public AskDemoArticleParser(String cacheActorPath, String httpClientActorPath, String artcileParseActorPath, Timeout timeout) {
        this.cacheActor = context().actorSelection(cacheActorPath);
        this.httpClientActor = context().actorSelection(httpClientActorPath);
        this.artcileParseActor = context().actorSelection(artcileParseActorPath);
        this.timeout = timeout;
    }

    /**
     * Note there are 3 asks so this potentially creates 6 extra objects:
     * - 3 Promises
     * - 3 Extra actors
     * It's a bit simpler than the tell example.
     */
    @Override
	public PartialFunction<Object, BoxedUnit> receive() {
        return ReceiveBuilder.match(ParseArticle.class, msg -> {
            final CompletionStage<Object> cacheResult = toJava(ask(cacheActor, new GetRequest(msg.url), timeout));
            final CompletionStage<Object> result = cacheResult.handle((x, t) -> {
                return (x != null) ? CompletableFuture.completedFuture(x)
                        : toJava(ask(httpClientActor, msg.url, timeout)).thenCompose(rawArticle -> toJava(
                                ask(artcileParseActor, new ParseHtmlArticle(msg.url, ((HttpResponse) rawArticle).body), timeout)));
            }).thenCompose(x -> x);

            final ActorRef senderRef = sender();
            result.handle((x, t) -> {
                if (x != null) {
                    if (x instanceof ArticleBody) {
                        String body = ((ArticleBody) x).body; // parsed article
                        cacheActor.tell(body, self()); // cache it
                        senderRef.tell(body, self()); // reply
                    } else if (x instanceof String) {
                        senderRef.tell(x, self());
                    }
                } else if (x == null) {
                    senderRef.tell(new akka.actor.Status.Failure(t), self());
                }
                return null;
            });

        }).build();
    }
}
