package com.mirbozorgi.zuul;

import com.mirbozorgi.zuul.filter.SimplePreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients
@ComponentScan({"demo.gateway.feigns", "demo.gateway"})
public class APIGateway {

  public static void main(String[] args) {
    SpringApplication.run(APIGateway.class, args);
  }

  @Bean
  public SimplePreFilter client() {
    return new SimplePreFilter();
  }

}