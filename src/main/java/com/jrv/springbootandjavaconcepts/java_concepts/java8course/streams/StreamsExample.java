package com.jrv.springbootandjavaconcepts.java_concepts.java8course.streams;

import com.jrv.springbootandjavaconcepts.java_concepts.java8course.data.Student;
import com.jrv.springbootandjavaconcepts.java_concepts.java8course.data.StudentDataBase;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamsExample {
    public static void main(String[] args) {
        simpleStreamsExample();
        mapStudentName();
        flatMapExample();
        sortedUsingComparator();
        filterExample();
        reduceExample();
        reduceExampleSumOfNotebooksByAllStudents();
        allMatchExample();
        streamOfIterateAndGenerate();
    }
    static List<Student> studentList = StudentDataBase.getAllStudents();

    private static void simpleStreamsExample() {
        /*List to Map Using Functions with Method Reference*/
        Map<String, List<String>> studentNameActivitiesMap = studentList.stream().collect(Collectors.toMap(Student::getName, Student::getActivities));
        System.out.println(studentNameActivitiesMap);

        /*Filtered Map*/
        Map<String, List<String>> studentNameActivitiesMapFiltered = studentList.stream().filter(student -> student.getGradeLevel()>=3).collect(Collectors.toMap(Student::getName, Student::getActivities));
        System.out.println(studentNameActivitiesMapFiltered);
    }

    /*Map Example*/
    public static void mapStudentName(){
        List<String> studentNames = studentList.stream().map(Student::getName).collect(toList());
        System.out.println(studentNames);
    }

    public static void flatMapExample(){
        List<String> studentActivities = studentList.stream()
                .map(Student::getActivities)
                .flatMap(Collection::stream)
                .distinct()
                .sorted()
                .collect(toList());
        System.out.println(studentActivities);
    }

    public static void sortedUsingComparator(){
        List<Student> sortedStudents = studentList.stream()
                .sorted(Comparator.comparing(Student::getName)) //use .reversed() for switching the sorting
                .collect(toList());
        System.out.println(sortedStudents);
    }

    public static void filterExample(){
        List<Student> filteredStudents = studentList.stream()
                .filter(student -> student.getGender().equals("female"))
                .collect(toList());
        System.out.println(filteredStudents);
    }

    public static void reduceExample(){
        /*To reduce to the student with highest GPA*/
        Optional<Student> studentWithHighestGPA = studentList.stream()
                .reduce((student1, student2) -> (student1.getGpa() > student2.getGpa()) ? student1 : student2);
        System.out.println(studentWithHighestGPA.isPresent());
        System.out.println(studentWithHighestGPA.get());
    }

    public static void reduceExampleSumOfNotebooksByAllStudents(){
        Optional<Integer> sumOfNotebooks = studentList.stream()
                .map(Student::getNoteBooks)
//                .reduce((a,b) -> a+b);
                .reduce(Integer::sum); // For minimum/maximum reduce(Integer::min)
        System.out.println("Sum of Notsebooks: " + sumOfNotebooks.get());
    }

    public static void allMatchExample() {
        Boolean allMatch = studentList.stream()
                .allMatch(student -> student.getGpa() >= 3); //min grade is 3.5 so 3 returns true and 4 returns false
        System.out.println("All Match : " + allMatch);
        /*Same kinda things for anyMatch and noneMatch*/
    }

    public static void streamOfIterateAndGenerate() {
        Stream<String> stringStream = Stream.of("adam","dan","jenny","dave");
        stringStream.forEach(System.out::println);

        List<Integer> integerList  = Stream.iterate(1, x->x*2)
                .limit(10)
                .map(Integer::new)
                .collect(toList());
        System.out.println("iterate : " + integerList);

        Supplier<Integer> supplier = new Random()::nextInt;
        List<Integer> integerList1  = Stream.generate(supplier)
                .limit(10)
                .collect(toList());
        System.out.println("generate : " + integerList1);
    }
}
