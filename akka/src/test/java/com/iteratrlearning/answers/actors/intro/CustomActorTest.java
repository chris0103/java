package com.iteratrlearning.answers.actors.intro;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.function.BiConsumer;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class CustomActorTest {

    private static final long TEST_TIMEOUT = 500L;

    private static void attemptUntilPasses(final Runnable runnable) {
        final long limit = System.currentTimeMillis() + TEST_TIMEOUT;

        AssertionError lastThrowable = null;
        while (limit > System.currentTimeMillis()) {
            try {
                runnable.run();
                lastThrowable = null;
                break;
            } catch (final AssertionError t) {
                lastThrowable = t;
            }
        }

        if (lastThrowable != null) {
            throw lastThrowable;
        }
    }

    private CustomActor<String> actor;

    private BiConsumer<CustomActor<String>, String> behaviourHandler = mock(BiConsumer.class);

    private BiConsumer<CustomActor<String>, Throwable> errorHandler = mock(BiConsumer.class);

    @Before
    public void setUp() {
        actor = ActorSystem.spawn(behaviourHandler, errorHandler);
    }

    @Test
    public void testAcceptMessage() {
        actor.send("Hello");
        attemptUntilPasses(() -> verify(behaviourHandler, times(1)).accept(actor, "Hello"));
    }

    @Test
    public void testErrorHandling() {
        Exception e = new RuntimeException("Fake Error");
        doThrow(e).when(behaviourHandler).accept(actor, "Hello");
        actor.send("Hello");
        attemptUntilPasses(() -> verify(errorHandler, times(1)).accept(actor, e));
    }
}
