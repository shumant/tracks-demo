# tracks-demo

Sample app for RESTful API and Frontend for Tracks and Cars persistence 

- Java 11
- Spring Boot 5 with embedded Tomcat
- OpenTable Embedded PostgreSQL for embedded Postgres
- Maven as build system

Unit-tests are omitted because the project is really tiny.
API workflow is presented in com.shuman.tracksdemo.TracksDemoApplicationTests.java 

To run tests and build jar:
> ./mvnw clean install

After that to run the app as server on port 8080:
> java -jar backend/target/backend-0.0.1-SNAPSHOT.jar
