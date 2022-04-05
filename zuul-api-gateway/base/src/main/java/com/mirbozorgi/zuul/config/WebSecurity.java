package com.mirbozorgi.zuul.config;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        .authorizeRequests()
        .antMatchers("/actuator/health").permitAll()
        .antMatchers("/actuator/**").hasRole("manager")
        .antMatchers("/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic();
  }
}