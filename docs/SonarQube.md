![SonarQube Logo](https://www.sonarqube.org/assets/logo-31ad3115b1b4b120f3d1efd63e6b13ac9f1f89437f0cf6881cc4d8b5603a52b4.svg)

**Step by step instructions to Implement SonarQube**

**1. Dependency and Plugins**

These plugins needs to be inserted in the **pom.xml**

    <plugin>
      <groupId>org.sonarsource.scanner.maven</groupId>
      <artifactId>sonar-maven-plugin</artifactId>
      <version>3.8.0.2131</version>
    </plugin>
    
    <plugin>
      <groupId>org.jacoco</groupId>
      <artifactId>jacoco-maven-plugin</artifactId>
      <version>0.8.5</version>
      <executions>
        <execution>
          <id>prepare-agent</id>
          <goals>
            <goal>prepare-agent</goal>
          </goals>
        </execution>
        <execution>
          <id>report</id>
          <goals>
            <goal>report</goal>
          </goals>
        </execution>
      </executions>
    </plugin>

Above contains the Sonar Maven Plugin and Jacoco Maven Plugin. Sonar Maven plugin provides a Sonar Scanner for Maven and Jacoco plugin is used to calculate the test coverage ratio.

**2. Setup SonarQube Server**

We also need to download SonarQube if we are testing our code quality locally. Go to this link : [**https://www.sonarqube.org/downloads/**](https://www.sonarqube.org/downloads/)  and just download community version download for free. Now Unzip the folder and need to start the SonarQube server . So go to SonarQube folder inside this folder go to **bin** folder and select your OS version . Here I am using windows 64-bit , so just opening folder **windows-x86–64** inside this folder we just need to double click on **StartSonar.bat** file and on command prompt we will see our SonarQube server has been started and its default port will be **9000**. Now go to browser and type [**http://localhost:9000**](http://localhost:9000/)  and here we can see SonarQube dashboard. The default **username and password** both will be *admin*.

**3. Maven Commands to Run**

Run these commands under Maven, with any Environment variables (like Database credentials, etc.) if you're using. Or if you're running in terminal, use **mvn** before the command.

First command will be used to generate code coverage report by Jacoco plugin.

    clean org.jacoco:jacoco-maven-plugin:prepare-agent install

Second command will be used to see the project code analysis on SonarQube dashboard. For this we need to pass an authorization token with **sonar:sonar** command . We will get this token from Sonarqube dashboard Click on My Account → Security → Enter Token Name and then click on Generate.

    sonar:sonar -Dsonar.login=generated-token-value

**References**
- [https://gainjavaknowledge.medium.com/spring-boot-code-analysis-with-sonarqube-4d953ca8f09e](https://gainjavaknowledge.medium.com/spring-boot-code-analysis-with-sonarqube-4d953ca8f09e)
- [https://medium.com/trendyol-tech/spring-boot-2-2-6-code-quality-with-sonarqube-8-2-community-70a76634bf75](https://medium.com/trendyol-tech/spring-boot-2-2-6-code-quality-with-sonarqube-8-2-community-70a76634bf75)