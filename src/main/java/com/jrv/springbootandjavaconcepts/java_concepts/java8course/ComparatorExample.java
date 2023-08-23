package com.jrv.springbootandjavaconcepts.java_concepts.java8course;

import java.util.Comparator;

public class ComparatorExample {
    public static void main(String[] args) {
        Comparator<Integer> integerComparator = (a,b) -> a.compareTo(b);
        System.out.println("Result using Comparator is: " + integerComparator.compare(4,2));
    }
}
