package com.mirbozorgi.security.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserDoseNotExistedException extends CustomException {

  public UserDoseNotExistedException() {

    super("user_not_existed", HttpStatus.NOT_ACCEPTABLE);
  }

}
