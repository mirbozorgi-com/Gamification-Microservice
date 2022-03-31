package com.mirbozorgi.security.mailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class InitialazeMailSender {

  @Bean
  public JavaMailSender initialize() {

    return new JavaMailSenderImpl();

  }

}
