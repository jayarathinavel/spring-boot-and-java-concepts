package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.completable_futures;

import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.CommonUtil.*;
import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {
    private HelloWorldService helloWorldService;

    public CompletableFutureHelloWorld(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public CompletableFuture<String> helloWorldCompletableFuture(){
        return CompletableFuture.supplyAsync(helloWorldService::helloWorld)
                .thenApply(String::toUpperCase);
    }

    public CompletableFuture<String> helloWorld_withSize(){
        Function<String, String> helloWorldWithSizeFunction = input ->
                (input.length() + " - " + input.toUpperCase());
        return CompletableFuture.supplyAsync(helloWorldService::helloWorld)
                .thenApply(result -> helloWorldWithSizeFunction.apply(result));
    }
    public static void main(String[] args) {
        HelloWorldService helloWorldService = new HelloWorldService();
        CompletableFuture.supplyAsync(()-> helloWorldService.helloWorld())
                .thenApply((result) -> result.toUpperCase())
                .thenAccept((result) -> {
                    log("Result is + " + result);
                })
                .join();
        log("Done");
//        delay(2000);
        helloWorldMultipleCalls();
        /* Other methods are ran using test cases */
    }

    public static void helloWorldMultipleCalls(){
        System.out.println(" helloWorldMultipleCalls() starts" );
        startTimer();
        HelloWorldService helloWorldService = new HelloWorldService();
        String hello = helloWorldService.hello();
        String world = helloWorldService.world();
        System.out.println(hello + world);
        timeTaken();
        System.out.println(" helloWorldMultipleCalls() ends" );
    }

    public String helloWorldMultipleAsyncCalls() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> helloWorldService.world());

        String helloWorldMultipleAsyncCalls = hello.thenCombine(world, (h, w) -> h+w)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return helloWorldMultipleAsyncCalls;
    }

    public String helloWorldThreeAsyncCalls() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> helloWorldService.world());
        CompletableFuture<String> hi = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi I am 3rd completable Future";
        });

        String helloWorldMultipleAsyncCalls = hello
                .thenCombine(world, (h, w) -> h+w)
                .thenCombine(hi, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return helloWorldMultipleAsyncCalls;
    }
}
