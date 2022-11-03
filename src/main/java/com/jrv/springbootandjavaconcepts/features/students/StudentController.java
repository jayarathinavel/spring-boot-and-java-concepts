package com.jrv.springbootandjavaconcepts.features.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(path = "{student-id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')")
    public Optional<Student> getStudentById(@PathVariable("student-id") Integer studentId){
        return studentRepository.findById(studentId);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<Object> registerNewStudent(@RequestBody Student student){
        studentRepository.save(student);
        return new ResponseEntity<>("Student Added Successfully", HttpStatus.OK);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<Object> deleteStudent(@PathVariable("studentId") Integer studentId){
        studentRepository.deleteById(studentId);
        return new ResponseEntity<>("Student Deleted Successfully", HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student){
        studentRepository.deleteById(student.getStudentId());
        studentRepository.save(student);
        return new ResponseEntity<>("Student Updated Successfully", HttpStatus.OK);

    }
}
