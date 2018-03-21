package com.evs.training.webevents.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfiguration {
  @Bean
  public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
    return new Jackson2ObjectMapperBuilder()
        .featuresToDisable(
            SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .featuresToEnable(
            SerializationFeature.WRITE_ENUMS_USING_TO_STRING
        )
        .indentOutput(true)
        .serializationInclusion(JsonInclude.Include.NON_NULL)

        ;

  }
}