![Liquibase Logo](https://www.liquibase.org/wp-content/themes/liquibase/assets/img/logo-org.svg)

**Step by step instructions of setting up Liquibase in Spring Boot**

**1. Dependency and Plugin**
Add the dependency and plugin to **pom.xml**
**Dependency :** *Liquibase Core*

    <dependency>  
	    <groupId>org.liquibase</groupId>  
	    <artifactId>liquibase-core</artifactId>
    </dependency>
**Plugin :** Liquibase Maven Plugin
Has the location of property file (application.properties)

    <plugin>  
        <groupId>org.liquibase</groupId>  
        <artifactId>liquibase-maven-plugin</artifactId>  
        <configuration>  
            <propertyFile>src/main/resources/application.properties</propertyFile>  
            <propertyFileWillOverride>true</propertyFileWillOverride>  
        </configuration>  
    </plugin>

**2. Property file**
Add the Database credentials and Liquibase change log location as mentioned below, in **application.properties** file

     #Database Credentials  
    spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_SCHEMA}  
    spring.datasource.username=${DB_USER_NAME}  
    spring.datasource.password=${DB_PASSWORD}  
    spring.datasource.driver-class-name=org.postgresql.Driver  
    spring.jpa.hibernate.ddl-auto=none  
    #For Liquibase  
    spring.liquibase.enabled=true  
    spring.liquibase.change-log=classpath:db/changelog/changelog-master.xml
**3. Change Log Files**
Under resources folder, create a directory **db** and inside db, create another directory **changelog**. Under the changelog folder, create the file **changelog-master.xml** and paste the content below.

    <?xml version="1.0" encoding="UTF-8"?>  
    <databaseChangeLog  
      xmlns="http://www.liquibase.org/xml/ns/dbchangelog"  
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
      xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog  
     http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">  
      
        <include file="db/changelog/changelog-variables.xml"/>  
    </databaseChangeLog>

This file is master changelog file, and the individual change log files are included here. (In the above example, change log file of the table variables is included) The change log file contains the Structure of the table created, any subsequent changes made, etc.

*The best practice is to have a separate change log file for each of the table in project.*

The change log file looks like the below one,

    <?xml version="1.0" encoding="UTF-8"?>  
    <databaseChangeLog  
      xmlns="http://www.liquibase.org/xml/ns/dbchangelog"  
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
      xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog  
     http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">
     
        <changeSet id="1" author="Jayarathinavel">  
            <createTable tableName="variables">  
                <column name="key" type="text">  
                    <constraints primaryKey="true" primaryKeyName="variables_pk"/>  
                </column>  
                <column name="value" type="text">  
                    <constraints nullable="false"/>  
                </column>  
            </createTable>  
        </changeSet>  
      
        <changeSet id="2" author="Jayarathinavel">  
            <insert tableName="variables">  
                <column name="key" value="APP_NAME"/>  
                <column name="value" value="Spring Boot and Java Concepts"/>  
            </insert>  
        </changeSet>  
      
    </databaseChangeLog>

**4. Start the Project**
After making the necessary changes, Start the application. It will make the changes to the database of corresponding environment during starting. And the same change will be noted down to not execute it again. For this we need to provide an unique id to each change set. 

**References**
- [https://www.youtube.com/watch?v=xjXHecGOy84](https://www.youtube.com/watch?v=xjXHecGOy84)
- [https://github.com/dicksonmulli/liquibase-with-springboot](https://github.com/dicksonmulli/liquibase-with-springboot)
- [https://docs.liquibase.com/tools-integrations/springboot/springboot.html](https://docs.liquibase.com/tools-integrations/springboot/springboot.html)