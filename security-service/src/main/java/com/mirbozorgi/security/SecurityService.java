package com.mirbozorgi.security;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"mirbozorgi.base", "demo"})
@EnableFeignClients(basePackages = {"mirbozorgi.base.feigns"})
public class SecurityService extends WebMvcConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(SecurityService.class, args);
  }

  @Bean
  public Sampler defaultSampler() {
    return Sampler.ALWAYS_SAMPLE;
  }

}
