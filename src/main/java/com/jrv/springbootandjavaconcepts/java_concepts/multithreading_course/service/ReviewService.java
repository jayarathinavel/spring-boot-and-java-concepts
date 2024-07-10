package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service;

import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.Review;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.LoggerUtil;
import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.CommonUtil.delay;

public class ReviewService {

    public Review retrieveReviews(String productId) {
        delay(1000);
        LoggerUtil.log("retrieveReviews after Delay");
        return new Review(200, 4.5);
    }
}
