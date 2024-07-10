package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Review {
    private int noOfReviews;
    private double overallRating;
}
