package com.mirbozorgi.reactor.security.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserExistAndEmailWrongException extends CustomException {

  public UserExistAndEmailWrongException() {

    super("user_exist_and_request_email_is_wrong", HttpStatus.NOT_ACCEPTABLE);
  }
}
