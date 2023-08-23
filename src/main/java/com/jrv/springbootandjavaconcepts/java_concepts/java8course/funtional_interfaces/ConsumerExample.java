package com.jrv.springbootandjavaconcepts.java_concepts.java8course.funtional_interfaces;

import com.jrv.springbootandjavaconcepts.java_concepts.java8course.data.Student;
import com.jrv.springbootandjavaconcepts.java_concepts.java8course.data.StudentDataBase;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerExample {
    static List<Student> allStudents = StudentDataBase.getAllStudents();
    public static void main(String[] args) {
        simpleConsumerExample();
        printAllStudents();
        printAllStudentsNameAndGender();
        printAllStudentNamesWithoutCreatingConsumerObject();
        printAllStudentsNameAndGenderBiConsumerExample();
        addTwoNumbersBiConsumerExample();
    }

    public static void simpleConsumerExample(){
        Consumer<String> consumer = (string) -> System.out.println(string.toUpperCase());
        consumer.accept("java");
    }

    public static void printAllStudents(){
        Consumer<Student> studentConsumer = (student) -> System.out.println(student);
        allStudents.forEach(studentConsumer);
    }

    public static void printAllStudentsNameAndGender(){
        Consumer<Student> studentNameConsumer = (student) -> System.out.println(student.getName());
        Consumer<Student> studentGenderConsumer = (student) -> System.out.println(student.getGender());
        allStudents.forEach(studentNameConsumer.andThen(studentGenderConsumer)); //andThen() - is called Consumer chaining
    }

    public static void printAllStudentNamesWithoutCreatingConsumerObject(){
        allStudents.forEach((student -> System.out.println(student.getName())));
    }

    public static void printAllStudentsNameAndGenderBiConsumerExample(){
        BiConsumer<String, String> studentNameAndGenderConsumer = (studentName, gender) -> System.out.println("Name: " + studentName + " Gender: " + gender);
        allStudents.forEach(student -> studentNameAndGenderConsumer.accept(student.getName(), student.getGender()));
    }

    public static void addTwoNumbersBiConsumerExample(){
        BiConsumer<Integer, Integer> addTwoNumbersConsumer = (a, b) -> System.out.println(a + b);
        addTwoNumbersConsumer.accept(1,2);
    }
}
