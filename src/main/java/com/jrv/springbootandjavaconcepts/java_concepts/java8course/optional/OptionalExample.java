package com.jrv.springbootandjavaconcepts.java_concepts.java8course.optional;

import java.util.Optional;

public class OptionalExample {
    String string = "Hell0";

    public static void hello(){
        OptionalExample optionalExample = new OptionalExample();
        System.out.println(optionalExample.string);
    }
    public static void main(String[] args) {
        hello();
        optionalOfOfNullableAndEmpty();
        optionalOrElseOrElseGetOrElseThrow();
        optionalIsPresentIfPresent();
    }

    private static void optionalIsPresentIfPresent() {
        Optional<String> optionalNull = Optional.ofNullable(null);
        Optional<String> optionalNonNull = Optional.ofNullable("Hello");
        System.out.println("Optional isPresent : " + optionalNull.isPresent());
        optionalNonNull.ifPresent(s -> System.out.println(s));
    }

    private static void optionalOrElseOrElseGetOrElseThrow() {
        Optional<String> optionalOfNullable = Optional.ofNullable(null);
        System.out.println("Optional orElse: " + optionalOfNullable.orElse("Default"));
        /* orElseGet - Accepts a Supplier instead of <T>
        * orElseThrow - throws a custom exception
        * */
    }

    private static void optionalOfOfNullableAndEmpty() {
        Optional<String> optionalOf = Optional.of("Hello");
        Optional<String> optionalOfNullable = Optional.ofNullable("World");
        Optional<String> optionalEmpty = Optional.empty();
        System.out.println("Optional of : " + optionalOf);
        System.out.println("Optional ofNullable : " + optionalOfNullable);
        System.out.println("Optional Empty : " + optionalEmpty);
    }
}
