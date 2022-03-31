package com.mirbozorgi.reactor.security.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ForgetPassTokenExpiredException extends CustomException {

  public ForgetPassTokenExpiredException() {

    super("forget_pass_token_expired", HttpStatus.NOT_ACCEPTABLE);
  }


}
