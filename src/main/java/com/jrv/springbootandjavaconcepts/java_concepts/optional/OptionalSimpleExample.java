package com.jrv.springbootandjavaconcepts.java_concepts.optional;

import java.util.Optional;

public class OptionalSimpleExample {
    public static void main(String[] args) {
        Person person = new Person("Jrv", null);
        String email = person.getEmail().map(String::toLowerCase).orElse("Email is not provided");
        System.out.println(email);
    }
}

class Person {
    private final String name;
    private final String email;

    Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }
}