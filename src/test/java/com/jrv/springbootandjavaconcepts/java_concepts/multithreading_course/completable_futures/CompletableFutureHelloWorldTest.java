package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.completable_futures;

import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {

    HelloWorldService helloWorldService = new HelloWorldService();
    CompletableFutureHelloWorld completableFutureHelloWorld = new CompletableFutureHelloWorld(helloWorldService);
    @Test
    void helloWorldCompletableFuture() {
        /* When */
        CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorldCompletableFuture();
        /* Assert */
        completableFuture.thenAccept(s->{
            assertEquals("HELLO WORLD", s);
        })
        .join();
    }

    @Test
    void helloWorldWithSizeCompletableFuture() {
        /* When */
        CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorld_withSize();
        /* Assert */
        completableFuture.thenAccept(s->{
                    assertEquals("11 - HELLO WORLD", s);
                })
            .join();
    }
    @Test
    void helloWorldMultipleAsyncCalls() {
        /* When */
        String helloWorldMultipleAsyncCalls = completableFutureHelloWorld.helloWorldMultipleAsyncCalls();
        /* Assert */
        assertEquals("HELLO WORLD!", helloWorldMultipleAsyncCalls);
    }

    @Test
    void helloWorldThreeAsyncCalls() {
        /* When */
        String helloWorldMultipleAsyncCalls = completableFutureHelloWorld.helloWorldThreeAsyncCalls();
        /* Assert */
        assertEquals("HELLO WORLD! HI I AM 3RD COMPLETABLE FUTURE", helloWorldMultipleAsyncCalls);
    }
}