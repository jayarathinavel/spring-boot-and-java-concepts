package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util;

public class LoggerUtil {

    public static void log(String message){

        System.out.println("[" + Thread.currentThread().getName() +"] - " + message);

    }
}
