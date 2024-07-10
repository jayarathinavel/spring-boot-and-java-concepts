package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service;

import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.*;

import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.CommonUtil.delay;

public class InventoryService {
    public Inventory retrieveInventory(ProductOption productOption) {
        delay(500);
        return Inventory.builder()
                .count(2).build();

    }
}
