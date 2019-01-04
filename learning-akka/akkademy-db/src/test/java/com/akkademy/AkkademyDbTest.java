package com.akkademy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.akkademy.messages.SetRequest;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;

public class AkkademyDbTest {

    private ActorSystem system = ActorSystem.create();

    @Test
    public void itShouldPlaceKeyValueFromSetMessageIntoMap() {
        TestActorRef<AkkademyDb> actorRef = TestActorRef.create(system, Props.create(AkkademyDb.class));
        actorRef.tell(new SetRequest("key", "value"), ActorRef.noSender());
        AkkademyDb akkademyDb = actorRef.underlyingActor();
        assertEquals("value", akkademyDb.map.get("key"));
    }
}
