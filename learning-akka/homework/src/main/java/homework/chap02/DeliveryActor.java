package homework.chap02;

import static akka.pattern.Patterns.ask;
import static scala.compat.java8.FutureConverters.toJava;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import pong.JavaPongActor;

public class DeliveryActor extends AbstractActor {

    protected final LoggingAdapter log = Logging.getLogger(context().system(), this);

    private ActorRef pongActor = context().actorOf(Props.create(JavaPongActor.class));

    private StringReverseService service = new StringReverseService();

    public DeliveryActor() {
        receive(ReceiveBuilder.match(String.class, str -> str.equals("Ping"), str -> {
            final ActorRef sender = sender();
            toJava(ask(pongActor, str, 2000)).thenAccept(ret -> sender.tell(ret, self()));
        }).match(String.class, str -> {
            sender().tell(service.reverseString(str), self());
        }).matchAny(x -> log.info("Received message of wrong type: {}.", x)).build());
    }
}
