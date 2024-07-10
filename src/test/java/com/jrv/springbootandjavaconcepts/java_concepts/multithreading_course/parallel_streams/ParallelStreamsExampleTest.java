package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.parallel_streams;

import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParallelStreamsExampleTest {

    ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();

    @Test
    void stringTransform() {
        /* Given */
        List<String> inputList = DataSet.namesList();
        /* When */
        List<String> resultList = parallelStreamsExample.stringTransform(inputList);
        /* Then */
        assertEquals(4, resultList.size());
        resultList.forEach(name -> {
            assertTrue(name.contains("- "));
        });
    }
}