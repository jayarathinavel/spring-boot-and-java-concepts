package com.jrv.springbootandjavaconcepts.java_concepts.java8course;

import com.jrv.springbootandjavaconcepts.java_concepts.java8course.data.Student;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MethodReferenceExample {
    public static void main(String[] args) {
        simpleLambdaVsMethodReferenceExample();
        notCompatibleToMethodReferenceExampleAndRefactoring();
        constructorReferenceExample();
    }

    public static void simpleLambdaVsMethodReferenceExample() {
        Function<String, String> toUpperCaseUsingLambda = s -> s.toUpperCase();
        Function<String, String> toUpperCaseUsingMethodReference = String::toUpperCase;
        System.out.println(toUpperCaseUsingLambda.apply("Hello"));
        System.out.println(toUpperCaseUsingMethodReference.apply("Hello"));
    }

    public static void notCompatibleToMethodReferenceExampleAndRefactoring(){
        Predicate<String> stringToNumberGreaterThan1Predicate = string -> Integer.parseInt(string) >= 1 ;
        System.out.println(stringToNumberGreaterThan1Predicate.test("5"));
        /*Needs refactoring the codes*/
        Predicate<String> stringToNumberGreaterThan1MethodReferencePredicate = MethodReferenceExample::stringToIntegerGreaterThan1;
        System.out.println(stringToNumberGreaterThan1MethodReferencePredicate.test("0"));
    }

    public static boolean stringToIntegerGreaterThan1(String string){
        return Integer.parseInt(string) >= 1;
    }

    public static void constructorReferenceExample(){
        Supplier<Student> studentSupplier = Student::new;
        System.out.println(studentSupplier.get());

        Function<String,Student> giveStudentNameAndGetStudentObjectFunction = Student::new;
        System.out.println(giveStudentNameAndGetStudentObjectFunction.apply("Velu"));
    }
}
