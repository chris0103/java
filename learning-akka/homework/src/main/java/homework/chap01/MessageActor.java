package homework.chap01;

import akka.actor.AbstractActor;
import akka.actor.Status;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

public class MessageActor extends AbstractActor {

    protected final LoggingAdapter log = Logging.getLogger(context().system(), this);

    private String message;

    @Override
    public PartialFunction<Object, BoxedUnit> receive() {
        return ReceiveBuilder.match(String.class, str -> {
            log.info("Received message {}.", str);
            message = str;
        }).matchAny(x -> sender().tell(new Status.Failure(new Exception("unknown message")), self())).build();
    }

    public String getMessage() {
        return message;
    }
}
