# A REST web app

## The problem

We're going to develop an application listing live events such as concerts,
conferences, or sport games. Meta data can be added to a given
event. The web app should either respond with JSON payload.

We will create a REST service using Spring Boot persisting data with JPA and
 use the Chrome app [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop)
 as client. 

## Technologies

### Spring

Let's first have a look to Spring. This framework is largely used in enterprise 
java applications, a good knowledge of Spring is necessary in many cases.
 
[Spring introduction](../spring/README.md)
 
### Spring Boot
 
Spring Boot is a project from Spring.io which allows to quickly create applications.

[Spring Boot introduction](../springboot/README.md)

 
### JPA

JPA is an API of JaveEE providing a persistence context. It's major implementation
is Hibernate. JPA will be used in this project to persist information in a database.

[JPA introduction](../jpa/README.md)


## Our server

### A basic Rest server with Spring Boot

With the help of [this tutorial](https://spring.io/guides/gs/rest-service/), create a basic Spring Boot application. 

### Add a health check

Add actuator dependency to give a "/health" mapping to your application.

### A route to add events

Add a POST route to add a JSON event.
Persist events in a Map

### A route to get events

Add GET route to get an event based on it's id

### Events persistence

Replace Map persistence by an H2 database storage

