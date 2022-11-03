package com.jrv.springbootandjavaconcepts.features.students;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "feature_students")
@Getter
@Setter
public class Student {

    @Id
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "student_name")
    private String studentName;
}
