package com.jrv.springbootandjavaconcepts.java_concepts.java8course;

import java.util.List;
import java.util.stream.Collectors;

public class Str {

    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring Boot", "API" , "Microservices","AWS", "PCF","Azure", "Docker", "Kubernetes");
        List<Integer> lengths = courses.stream().map(String::length).collect(Collectors.toList());
        Integer sum = lengths.stream().reduce(0, Integer::sum);
    }
}
