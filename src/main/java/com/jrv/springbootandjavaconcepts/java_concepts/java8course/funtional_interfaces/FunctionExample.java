package com.jrv.springbootandjavaconcepts.java_concepts.java8course.funtional_interfaces;

import java.util.function.Function;

public class FunctionExample {
    static Function<String, String> changeToUpperCase = (name) -> name.toUpperCase();

    public static void main(String[] args) {
        changeToUpperCase();
    }

    private static void changeToUpperCase() {
        System.out.println(changeToUpperCase.apply("java"));
    }

    /*
    *  andThen() and compose() for chaining the functions
    *  Function1.andThen(Function2) - executes the Function1 first and then Function2
    *  Function1.compose(Function2) - executes the Function2 first and then Function1
    * */
}
