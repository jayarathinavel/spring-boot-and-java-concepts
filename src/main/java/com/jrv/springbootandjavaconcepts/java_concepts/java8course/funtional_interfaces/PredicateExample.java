package com.jrv.springbootandjavaconcepts.java_concepts.java8course.funtional_interfaces;

import com.jrv.springbootandjavaconcepts.java_concepts.java8course.data.Student;
import com.jrv.springbootandjavaconcepts.java_concepts.java8course.data.StudentDataBase;

import java.util.List;
import java.util.function.Predicate;

public class PredicateExample {
    static Predicate<Integer> evenNumberPredicate = (a) -> a%2==0;
    static Predicate<Integer> divisibleByFivePredicate = a -> a%5==0;
    public static void main(String[] args) {
        findIfTheGivenNumberIsEvenNumber();
        evenNumberAndDivisibleByFive();
        evenNumberOrDivisibleByFive();
        negate();
        predicateStudentExample();
    }

    public static void findIfTheGivenNumberIsEvenNumber() {
        System.out.println(evenNumberPredicate.test(4));
    }

    public static void evenNumberAndDivisibleByFive(){
        System.out.println(evenNumberPredicate.and(divisibleByFivePredicate).test(10));
        System.out.println(evenNumberPredicate.and(divisibleByFivePredicate).test(8));
    }

    public static void evenNumberOrDivisibleByFive(){
        System.out.println(evenNumberPredicate.or(divisibleByFivePredicate).test(10));
        System.out.println(evenNumberPredicate.or(divisibleByFivePredicate).test(8));
    }

    public static void negate(){
        System.out.println(evenNumberPredicate.negate().test(3));
    }

    public static void predicateStudentExample(){
        List<Student> studentList = StudentDataBase.getAllStudents();
        Predicate<Student> gradeLevelPredicate = student -> student.getGradeLevel() >= 3;
        Predicate<Student> genderPredicate = student -> student.getGender().equals("male");
        studentList.forEach(student -> {
            if(gradeLevelPredicate.and(genderPredicate).test(student)){
                System.out.println(student);
            }
        });
    }


}
