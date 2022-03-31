package com.mirbozorgi.springcloud.apigateway;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.mirbozorgi.springcloud")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringCloudApiGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudApiGatewayApplication.class, args);
  }

  @Bean
  public Sampler defaultSampler() {
    return Sampler.ALWAYS_SAMPLE;
  }
}
