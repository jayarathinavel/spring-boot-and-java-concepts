package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service;

import java.util.concurrent.CompletableFuture;

import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.CommonUtil.delay;
import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.LoggerUtil.log;

public class HelloWorldService {

    public  String helloWorld() {
        delay(1000);
        log("inside helloWorld");
        return "hello world";
    }

    public  String hello() {
        delay(1000);
        log("inside hello");
        return "hello";
    }

    public  String world() {
        delay(1000);
        log("inside world");
        return " world!";
    }

    public CompletableFuture<String> worldFuture(String input) {
        return CompletableFuture.supplyAsync(()->{
            delay(1000);
            return input+" world!";
        });
    }

}
