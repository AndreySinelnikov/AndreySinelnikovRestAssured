#### GOAL: Add basic REST Assured/Testng setup to the project

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
   so it points to TestNG xml file location. 
   (EXAMPLE: _${project.basedir}/src/test/resources/...}_)
   
--------
