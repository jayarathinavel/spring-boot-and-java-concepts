package com.jrv.springbootandjavaconcepts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * To use as a Base Controller.
 *
 * @author Jayarathinavel
 * @since 29/Jul/2022
 */
@RestController
public class MainController {

    @Autowired
    private Variables variables;

    @GetMapping("/hello")
    public String helloWorld(){
        String variableValue = variables.getValue("APP_NAME");
        return Constants.HELLO_WORLD + " Welcome to " + variableValue;
    }
}
