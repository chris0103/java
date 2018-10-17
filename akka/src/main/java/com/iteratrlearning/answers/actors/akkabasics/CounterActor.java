package com.iteratrlearning.answers.actors.akkabasics;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class CounterActor extends UntypedActor {

    private int count = 0;

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public int getCount() {
        return count;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        log.info("Message received: " + message);
        if ("Count".equals(message)) {
            getSender().tell(count, getSelf());
        } else if ("Stop".equals(message)) {
            getContext().stop(self());
        }
        count++;
    }
}
