
# Shop Apotheke Coding Task

SpringBoot Application that demonstrates REST API Development using Spring Boot, using Java 11 features

## Features

This application has below REST Controller that support Instruments statistics


### Popular Repository Controller
1. Get /api/mostpopular/repository?limit=2

2. GET /api/mostpopular/repository/search/?dateOnwards=2019-01-05



## Technologies used

1. Java 11
2. Spring Boot
3. JUnit5, with Spring Testing (Unit & Integration Testing)


## Getting Started

The source code can be checked out to your local and then build and run the application either from your IDE after importing to it as a maven project, or just from a command line. Follow these steps for the command-line option:

### Prerequisites
1. Java 11
2. Maven 3



### Installing & Running

####  Build using maven

```
mvn clean install
```

#### Start the app

```
mvn spring-boot:run
```

## Swagger Documentation

API documentation can be accessed via [Swagger UI](http://localhost:8080/swagger-ui/index.html)

####  Test using maven

```
mvn clean Test or mvn clean integration-test
```

## Test Cases coverage Report
![Alt text](imgt.png?raw=true "Title")

