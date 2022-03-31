package com.mirbozorgi.reactor.security.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ForgetPassTokenException extends CustomException {

  public ForgetPassTokenException() {

    super("forget_pass_token_is_wrong", HttpStatus.NOT_ACCEPTABLE);
  }
}
