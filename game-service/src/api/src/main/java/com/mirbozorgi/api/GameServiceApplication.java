package com.mirbozorgi.api;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mirbozorgi", "mirbozorgi.base"})
@EntityScan(basePackages = {"com.mirbozorgi.core.entiry", "com.mirbozorgi.core.document"})
@EnableJpaRepositories(basePackages = "com.mirbozorgi.core.repository")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"mirbozorgi.base.feigns"})
@EnableMongoRepositories("com.mirbozorgi.core.repository.docuemnt")

public class GameServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(GameServiceApplication.class, args);
  }

  @Bean
  public Sampler defaultSampler() {
    return Sampler.ALWAYS_SAMPLE;
  }


}
