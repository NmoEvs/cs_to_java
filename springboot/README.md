# Spring Boot

Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications
that you can "just run". It takes an opinionated view of the Spring platform and third-party 
libraries so you can get started with minimum fuss. Most Spring Boot applications need very 
little Spring configuration.

## Features

* Create stand-alone Spring applications
* Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files)
* Automatically configure Spring whenever possible
* Provide production-ready features such as metrics, health checks and externalized configuration
* Absolutely no code generation and no requirement for XML configuration

The [reference guide](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle)
 includes detailed descriptions of all the features, plus an extensive howto for common use cases.

## Minimal sample

Here is a sample code to generate a web application

### Gradle dependency

```Groovy
dependencies {
    compile("org.springframework.boot:spring-boot-starter-web:1.5.2.RELEASE")
}
```

### Simple controller

```Java

package hello;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}

```

## Reference

* [Spring Boot reference guide](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle)
