package com.jrv.springbootandjavaconcepts.java_concepts.java8course;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Java8LocalDateAndTime {
    public static void main(String[] args) {
        localTime();
        localDate();
        localDateTime();
    }

    private static void localDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime: " + localDateTime);
    }

    private static void localDate() {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate: " + localDate);
    }

    private static void localTime() {
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime: " + localTime);
    }
}
