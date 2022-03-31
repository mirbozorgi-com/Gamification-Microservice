package com.mirbozorgi.reactor.security.controller;

import com.mirbozorgi.reactor.security.service.SecurityReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/security")
public class ReactiveController {

  @Autowired
  private SecurityReactiveService securityReactiveService;


  @PostMapping("/authorize")
  public Object authorize(
      @RequestHeader String token) {
    return securityReactiveService.authorize(token);
  }
}
