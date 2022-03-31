package com.mirbozorgi.reactor.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@AutoConfigurationPackage
@SpringBootApplication
@EnableR2dbcRepositories(basePackages = "com.mirbozorgi.reactor.security.lucifer.repository")
@EntityScan(basePackages = {"com.mirbozorgi.reactor.security.lucifer.entity"})
@ComponentScan(basePackages = {"mirbozorgi.base", "com.mirbozorgi.reactor.security"})
@EnableDiscoveryClient
public class SecurityReactiveServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SecurityReactiveServiceApplication.class, args);
  }

}
