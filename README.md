#### REFERENCE: Usage guide for REST Assured

- An adequate usage guide from dev team can be found [here](https://github.com/rest-assured/rest-assured/wiki/Usage).
------------

#### GOAL: Add basic REST Assured/TestNG setup to the project

1. IN *pom.xml*, ADD:

```xml
    <project>
      [...] 
      <properties>
        <maven.compiler.source></maven.compiler.source>
        <maven.compiler.target></maven.compiler.target>
        <rest-assured-all.version></rest-assured-all.version>
        <testng.version></testng.version>
        <maven-surefire-plugin></maven-surefire-plugin>
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

2. IN *pom.xml* property section, ADD property values specifying desired 
   compiler settings and dependency versions.
3. IN *src/test/resources*, ADD TestNG xml file pointing to test location.
4. IN *pom.xml*, ADD value to _<maven.surefire.plugin.suiteXmlFile>_ 
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

#### GOAL: Utilize Apache HTTP Status Codes for Java API tests

NOTE: Apache HTTP components offer unified set of things like status codes,
        eliminating problems stemming from coding them from scratch.
  
1. IN *pom.xml*, ADD 'httpcore-nio' artifact dependency from Maven Central Repo.
2. IN relevant class, ADD `import org.apache.http.HttpStatus;`.
   
----------

#### GOAL: Outline basic project structure

NOTE: The following structure (courtesy of EPAM training center) 
      should offer a fine decoupled template for tests utilizing DTO.

* *src/main/java/service* contains:
  - Common Service class 
    (deals with RequestSpecification which allows to
    specify common behavior of requests, i.e. base URI, headers, logging; <br>
    also, provides methods that wrap baseline REST Assured requests and
    may include low-level response checks, i.e. status code assertions)
  - REST {service_name} Service class for each API service 
    (provides higher-level methods corresponding to actual API methods)
    
* *src/main/java/assertions* contains:
  - REST {service_name} Assertions class for each API service 
    (provides methods that allow comparison between DTOs by desired fields, 
    though DTO/request comparison is fine too. Methods return `this` to allow chaining.
    NOTE: Putting error messages here also helps to avoid duplication.)
    
  - URI class (holds values for endpoints to be appended to baseURI)
    
* *src/main/java/dto* contains Data Transfer Object classes.

* *src/main/java/parameters* contains utility classes that make Yandex Speller 
  parameters and codes used throughout the program more human-readable.
    


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

#### GOAL: Outline basic test structure for this project template

* First, a test utilizes API method service to get a response in the form of a DTO array.
  EXAMPLE:
  `SpellingErrorDto[] spellingErrors = new RestCheckTextService().checkSingleText(text);`
  
* Then a test utilizes this API method's Assertions class to run a high-level assertion (or assertion chain).
   EXAMPLE:
  `new RestCheckTextServiceAssertions(spellingErrors).verifySpellingErrorsQuantity(expectedNumberOfErrors);`
  
------------

#### GOAL: Add test ID to data provider items

NOTE: Test ID is an additional clarification of a role of a given row of data in data provider.

1. IN test method signature, ADD a String 'testId' parameter before the parameters 
   actually used in a test.
   
2. IN each row of data in this test's data provider, ADD a descriptive element before the values actually used in the test.

-------------


   


