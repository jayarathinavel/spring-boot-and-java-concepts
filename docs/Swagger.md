![Swagger Logo](https://static1.smartbear.co/swagger/media/assets/images/swagger_logo.svg)

URL to Access Swagger : {BASE_URL}/swagger-ui.html

_Eg: http://localhost:8080/swagger-ui.html_


**Steps to Implement Swagger :**
 - Add **Spring Fox Swagger** and **Swagger UI dependency**

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
 - Add the Annotation `@EnableSwagger2` in the Configuration File or the Main Class file
 - The above two steps are enough to Initialize Swagger.
    
    Go to : `/swagger-ui.html` for Swagger UI and `/v2/api-docs` for JSON Documentation
 - For more Swagger Configuration/Customization use Docket Spring Bean

   
      return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .paths(PathSelectors.ant("/public/**"))
      .apis(RequestHandlerSelectors.basePackage("com.jrv.springbootandjavaconcepts.api"))
      .build()

- And more things can be customized in Docket
- And for Adding notes to an API, use `@ApiOperation` annotation in APIs method. 
- In case of any error, set this property `spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER
  ` in **application.properties**

References : 
- https://www.youtube.com/watch?v=gduKpLW_vdY
- https://www.youtube.com/watch?v=8s9I1G4tXhA