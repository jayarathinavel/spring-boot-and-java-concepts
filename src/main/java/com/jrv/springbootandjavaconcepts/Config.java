package com.jrv.springbootandjavaconcepts;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class Config {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Docket swaggerConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/public/**"))
                .apis(RequestHandlerSelectors.basePackage("com.jrv.springbootandjavaconcepts.api"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Spring Boot and Java Concepts API Info",
                "A project with Simple APIs I built for Practicing",
                "1.0",
                "Free to use",
                new Contact("Jayarathinavel",
                        "https://jayarathinavel.github.io/jayarathinavel/",
                        "jayrathinavel@gmail.com"),
                "No License",
                "https://jayarathinavel.github.io/jayarathinavel/",
                Collections.emptyList());
    }
}
