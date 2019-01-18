package com.akkademy;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;

import com.akkademy.messages.KeyNotFoundException;

public class JClientIntegrationTest {

    private static final JClient client = new JClient("127.0.0.1:2552");

    @Test
    public void itShouldFailedOnMissingRecord() throws Exception {
        client.set("123", 123);
        CompletableFuture<Object> future = (CompletableFuture<Object>) client.get("321").exceptionally(t -> {
            if (t instanceof KeyNotFoundException) {
                return constructErrorMessage((KeyNotFoundException) t);
            }
            return t.getMessage();
        });
        assertEquals(constructErrorMessage(new KeyNotFoundException("321")), future.get());
    }

    @Test
    public void itShouldSetRecord() throws Exception {
        client.set("123", 123);
        Integer result = (Integer) ((CompletableFuture<Object>) client.get("123")).get();
        assertEquals(Integer.valueOf(123), result);
    }

    private String constructErrorMessage(KeyNotFoundException t) {
        return t.getClass() + "[" + t.getKey() + "]";
    }
}
