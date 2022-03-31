package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserNotRegisteredException extends CustomException {

  public UserNotRegisteredException() {
    super("user_not_registered", HttpStatus.NOT_FOUND);
    this.setData("user_not_registered");
  }
}
