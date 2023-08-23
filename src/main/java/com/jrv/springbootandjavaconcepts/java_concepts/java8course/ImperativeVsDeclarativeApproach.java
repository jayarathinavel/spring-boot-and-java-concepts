package com.jrv.springbootandjavaconcepts.java_concepts.java8course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ImperativeVsDeclarativeApproach {
    public static void main(String[] args) {
        /*To remove the Duplicates from a List*/
        List<Integer> integerList = Arrays.asList(1,2,3,3,3,4,5,5);

        /*Imperative approach - How*/
        List<Integer> uniqueListImperative = new ArrayList<>();
        for(Integer integer: integerList){
            if(!uniqueListImperative.contains(integer)){
                uniqueListImperative.add(integer);
            }
        }
        System.out.println("Unique List Imperative: " + uniqueListImperative);

        /*Declarative Approach - What (Functional Programming)*/
        List<Integer> uniqueListDeclarative = integerList.stream().distinct().collect(Collectors.toList());
        System.out.println("Unique List Declarative: " + uniqueListDeclarative);
    }
}
