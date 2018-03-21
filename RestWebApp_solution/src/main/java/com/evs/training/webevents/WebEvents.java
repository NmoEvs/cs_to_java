package com.evs.training.webevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WebEvents {

  public static void main(String[] args) {
    SpringApplication.run(WebEvents.class, args).registerShutdownHook();
  }

}
