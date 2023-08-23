package com.jrv.springbootandjavaconcepts.java_concepts.java8course.streams;

import com.jrv.springbootandjavaconcepts.java_concepts.java8course.data.Student;
import com.jrv.springbootandjavaconcepts.java_concepts.java8course.data.StudentDataBase;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamsTerminalOperations {
    public static void main(String[] args) {
        /*Collector examples*/
        joiningCollectorExample();
        countingCollectorExample();
        mappingCollectorExample();
        minByAndMaxByExample();
        summingIntAndAveragingIntExample();
        groupingByExample();
        groupingByTwoParametersExample();
        /*PartitioningBy() is similar to groupingBy() - it accepts Predicate*/
    }

    private static void groupingByTwoParametersExample() {

        Map<Integer, Map<String, List<Student>>> multipleStudentMap = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy(Student::getGradeLevel,
                        Collectors.groupingBy(student -> student.getGpa() >=3.8 ? "OUTSTANDING" : "AVERAGE")));
        System.out.println("Multiple Level Mapping: " + multipleStudentMap);

        Map<Integer, Integer> gradeLevelAndSumOfNotebooks = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy(
                        Student::getGradeLevel,
                        Collectors.summingInt(Student::getNoteBooks)
                ));
        System.out.println("Student grade level and number of books : " + gradeLevelAndSumOfNotebooks);
    }

    private static void groupingByExample() {
        Map<String, List<Student>> studentGroupedBasedOnGender = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy(Student::getGender));
        System.out.println("Grouped Student: " + studentGroupedBasedOnGender);

        Map<String, List<Student>> studentGroupedBasedOnCustomizedKey = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy(student -> student.getGpa() >=3.8 ? "OUTSTANDING" : "AVERAGE"));
        System.out.println("Grouped Student using customized key: " + studentGroupedBasedOnCustomizedKey);

    }

    private static void summingIntAndAveragingIntExample() {
        /*Similar kind for Double and Long is availabe*/
        Integer totalNumberOfNoteBooks = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.summingInt(Student::getNoteBooks));
        System.out.println("Total Number of Notebooks : " + totalNumberOfNoteBooks);
        /*Similar kind for Averaging Int*/

    }

    private static void minByAndMaxByExample() {
        Optional<Student> minByStudent = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.minBy(Comparator.comparing(Student::getGpa)));
        System.out.println("Minimum grade Student: " + minByStudent);
        /*Similar for Max By*/
    }

    private static void mappingCollectorExample() {
        List<String> studentNames = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.mapping(Student::getName, Collectors.toList()));
        /* This is similar to
        *
        *   .stream()
            .map(Student::getName)
            .collect(Collectors.toList());
        */
        System.out.println("List of Student Names : " + studentNames);
    }

    private static void countingCollectorExample() {
        Long studentsCount = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.counting());
        System.out.println("No of students : " +studentsCount);
    }

    private static void joiningCollectorExample() {
        /*String Concatenation*/
        /*Also has Overloaded versions of joining() with delimiter, prefix and suffix*/
        String joinedStudentNames = StudentDataBase.getAllStudents()
                .stream()
                .map(Student::getName)
                .collect(Collectors.joining());
        System.out.println("Joined student names: " + joinedStudentNames);
    }
}
