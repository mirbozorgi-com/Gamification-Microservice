package com.mirbozorgi.zipkin.web;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/actuator/health").permitAll()
        .antMatchers("/**").hasRole("manager")
        .anyRequest().authenticated()
        .and()
        .httpBasic();
  }
}