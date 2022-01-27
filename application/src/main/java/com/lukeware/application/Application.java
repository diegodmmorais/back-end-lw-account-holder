package com.lukeware.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Diego Morais
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }
}
