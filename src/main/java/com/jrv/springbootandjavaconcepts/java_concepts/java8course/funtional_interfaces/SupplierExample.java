package com.jrv.springbootandjavaconcepts.java_concepts.java8course.funtional_interfaces;

import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        Supplier<String> supplierExample = () -> "Hello World";
        System.out.println(supplierExample.get());
    }
}
