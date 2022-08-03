package com.jrv.springbootandjavaconcepts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MainControllerTest {

    @Autowired
    private MainController mainController;

    @Test
    void helloWorldTest(){
        Assertions.assertTrue(mainController.helloWorld().contains("Hello"));
    }
}
