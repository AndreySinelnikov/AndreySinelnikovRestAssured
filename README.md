#### REFERENCE: Usage guide for REST Assured

- An adequate usage guide from dev team can be found [here](https://github.com/rest-assured/rest-assured/wiki/Usage).
------------

#### GOAL: Add basic REST Assured/TestNG setup to the project

1. IN *pom.xml*, ADD:

```xml
    <project>
      [...]
      <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <rest-assured-all.version>4.4.0</rest-assured-all.version>
        <testng.version>7.4.0</testng.version>
        <maven-surefire-plugin>3.0.0-M5</maven-surefire-plugin>
        <maven.surefire.plugin.suiteXmlFile></maven.surefire.plugin.suiteXmlFile>
      </properties>
    
      <build>
        <plugins>
            <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-surefire-plugin</artifactId>
              <version>${maven-surefire-plugin}</version>
              <configuration>
                <suiteXmlFiles>
                  <suiteXmlFile>${maven.surefire.plugin.suiteXmlFile}</suiteXmlFile>
                </suiteXmlFiles>
              </configuration>
            </plugin>
        </plugins>
      </build>
    
      <dependencies>
        <dependency>
          <groupId>io.rest-assured</groupId>
          <artifactId>rest-assured</artifactId>
          <version>${rest-assured.version}</version>
          <scope>test</scope>
        </dependency>
    
        <dependency>
          <groupId>org.testng</groupId>
          <artifactId>testng</artifactId>
          <version>${testng.version}</version>
          <scope>test</scope>
        </dependency>
      </dependencies>
      
      <profiles>
            <profile>
                <id>default-profile</id>
                <properties>
                    <maven.surefire.plugin.suiteXmlFile></maven.surefire.plugin.suiteXmlFile>
                </properties>
            </profile>
      </profiles>
    [...]
  </project>
```

2. IN *src/test/resources*, ADD TestNG xml file pointing to test location.
3. IN *pom.xml*, ADD value to _<maven.surefire.plugin.suiteXmlFile>_ 
   so it points to TestNG xml file location. Update default profile accordingly.
   (EXAMPLE: _${project.basedir}/src/test/resources/...}_)
   
--------
#### GOAL: Set static imports for common REST Assured-related methods

*NOTE: Recommended by the framework's creators to simplify coding.*

1. IN a relevant class, ADD:
   ```
   import static io.restassured.RestAssured.*;
   import static io.restassured.matcher.RestAssuredMatchers.*;
   import static org.hamcrest.Matchers.*;
   ```
   
----------

#### GOAL: Set up Apache HTTP Components dependency for Java API tests

NOTE: Apache HTTP components offer unified set of things like status codes,
        eliminating problems stemming from coding them from scratch.
  
1. IN *pom.xml*, ADD dependency from Maven Central Repo. 
   (PENDING: 'httpcore-nio' works, might investigate it later, though)
   
----------

#### GOAL: Outline basic project structure

NOTE: The following structure is *

* *src/main/java/service* contains:
  - Common Service class 
    (deals with RequestSpecification which allows to
    specify common behavior of requests, i.e. base URI, headers, logging; <br>
    also, provides methods that wrap baseline REST Assured requests and
    may include low-level response checks, i.e. status code assertions)
    
  - REST {service_name} Service class for each API service 
    (provides higher-level methods corresponding to actual API methods)
    
  - REST {service_name} Assertions class for each API service (TODO: check if true!)
    
  - URI class (holds values for endpoints to be appended to baseURI)
    
* *src/main/java/dto* contains Data Transfer Object classes.
    
 !!!!!! FINALIZE

-----------

#### GOAL: Implement DTO (Data Transfer Object) class for a JSON response body

1. IN *src/main/java/dto* OR {relevant_directory}, ADD class named
   something like '{class_name}Dto'.
   
2. IN *pom.xml*, ADD Lombok dependency.

3. IN the relevant class, ADD something like this:

```
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ClassnameDto {
	private Type fieldName;
	private List<Type> arrayFieldName;
	...
```

NOTE: Accessors annotation allows object to be returned in a manner similar
      to fluent object behavior (`this` instead of `void`) so you can utilize chaining 
      (like with intermediate methods in a builder).

NOTE: JSON response values that look like real numbers have to 
      be declared with Float type values in DTO. Integer-looking
      numbers map to Long.

NOTE: JSON fields that are themselves objects of different class
      should be declared as attributes of their own object type (so you
      will probably have to implement another DTO class for these objects).

------------
   


