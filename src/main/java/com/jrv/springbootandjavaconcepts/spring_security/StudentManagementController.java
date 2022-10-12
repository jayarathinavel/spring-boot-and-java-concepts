package com.jrv.springbootandjavaconcepts.spring_security;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/students")
public class StudentManagementController {
    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "Jon Snow"),
            new Student(2, "Danny"),
            new Student(3, "Arya")
    );

    @GetMapping
    public List<Student> getAllStudents(){
        return STUDENT_LIST;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        System.out.println("Register Student : \n" + student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println("Delete Student \n" + studentId);
    }

    @PutMapping(path = {"studentId"})
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        System.out.printf("Update Student \n" + "%s, %s%n", studentId, student);
    }

}
