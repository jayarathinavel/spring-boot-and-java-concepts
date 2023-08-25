package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.parallel_streams;

import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;

import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.CommonUtil.*;
import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.LoggerUtil.log;

public class ParallelStreamsExample {

    public List<String> stringTransform(List<String> namesList) {
        /*Using parallel streams - time taken : 0.5secs*/
        return namesList
                .stream().parallel()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
        /*Using Streams - Time taken : 2secs*/
/*
        return namesList
                .stream()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
*/

    }
    public static void main(String[] args) {
        List<String> namesList = DataSet.namesList();
        ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();
        startTimer();
        List<String> resultList = parallelStreamsExample.stringTransform(namesList);
        log("resultList : " + resultList);
        timeTaken();
    }

    private String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }
    public static List<String> namesList(){
        return List.of("Bob", "Jamie", "Jill", "Rick");
    }

    public List<String> string_toLowerCase(List<String> namesList){
        return namesList
                .stream()
                .parallel()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}
