package com.mirbozorgi.reactor.security.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class PasswordException extends CustomException {

  public PasswordException() {

    super("password_wrong", HttpStatus.NOT_ACCEPTABLE);
  }

}
