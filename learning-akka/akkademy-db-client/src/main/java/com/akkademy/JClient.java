package com.akkademy;

import static akka.pattern.Patterns.ask;
import static scala.compat.java8.FutureConverters.toJava;

import java.util.concurrent.CompletionStage;

import com.akkademy.messages.GetRequest;
import com.akkademy.messages.SetRequest;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;

public class JClient {

    private final ActorSystem system = ActorSystem.create("LocalSystem");

    private final ActorSelection remoteDb;

    public JClient(String remoteAddress) {
        remoteDb = system.actorSelection("akka.tcp://akkademy@" + remoteAddress + "/user/akkademy-db");
    }

    public CompletionStage<Object> get(String key) {
        return toJava(ask(remoteDb, new GetRequest(key), 2000));
    }

    public CompletionStage<Object> set(String key, Object value) {
        return toJava(ask(remoteDb, new SetRequest(key, value), 2000));
    }
}
