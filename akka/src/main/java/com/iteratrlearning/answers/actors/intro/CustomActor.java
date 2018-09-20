package com.iteratrlearning.answers.actors.intro;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;

public class CustomActor<T> implements Runnable {

    static <T> CustomActor<T> create(BiConsumer<CustomActor<T>, T> behaviourHandler, BiConsumer<CustomActor<T>, Throwable> errorHandler) {
        return new CustomActor<>(behaviourHandler, errorHandler);
    }

    private final BiConsumer<CustomActor<T>, T> actionHandler;

    private final BiConsumer<CustomActor<T>, Throwable> errorHandler;

    private final Queue<T> mailbox;

    private CustomActor(BiConsumer<CustomActor<T>, T> behaviourHandler, BiConsumer<CustomActor<T>, Throwable> errorHandler) {
        this.mailbox = new ConcurrentLinkedQueue<>();
        this.actionHandler = behaviourHandler;
        this.errorHandler = errorHandler;
    }

    @Override
    public void run() {
        while (true) {
            T message = mailbox.poll();
            if (message != null) {
                try {
                    actionHandler.accept(this, message);
                } catch (Exception e) {
                    errorHandler.accept(this, e);
                }
            }
        }
    }

    public void send(T message) {
        mailbox.offer(message);
    }
}
