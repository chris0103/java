package com.iteratrlearning.answers.actors.akkabasics;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.PatternsCS;
import akka.testkit.JavaTestKit;
import akka.testkit.TestActorRef;
import akka.util.Timeout;
import scala.concurrent.duration.Duration;

public class CounterActorPart2Test {

    ActorSystem system;

    @Before
    public void setup() {
        system = ActorSystem.create();
    }

    @After
    public void teardown() {
        JavaTestKit.shutdownActorSystem(system);
    }

    @Test(timeout = 2_000)
    public void testTellCount() {
        Props props = Props.create(CounterActor.class);
        TestActorRef<CounterActor> ref = TestActorRef.create(system, props, "test-counter-actor");
        final int ITERATIONS = 5;
        for (int i = 0; i < ITERATIONS; i++) {
            ref.tell("Status", null);
        }
        Timeout timeout = new Timeout(Duration.create(5, "seconds"));
        CompletableFuture<Object> ask = PatternsCS.ask(ref, "Count", timeout).toCompletableFuture();
        assertEquals(ITERATIONS, ask.join());
    }
}
