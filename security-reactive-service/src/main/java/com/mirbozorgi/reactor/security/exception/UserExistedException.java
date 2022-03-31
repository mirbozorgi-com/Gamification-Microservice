package com.mirbozorgi.reactor.security.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserExistedException extends CustomException {

  public UserExistedException() {
    super("user_existed", HttpStatus.NOT_ACCEPTABLE);
  }

}
