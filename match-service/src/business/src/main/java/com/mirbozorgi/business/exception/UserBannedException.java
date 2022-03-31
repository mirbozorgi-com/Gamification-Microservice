package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserBannedException extends CustomException {


  public UserBannedException() {
    super("user_banned", HttpStatus.NOT_ACCEPTABLE);
    this.setData("user_banned");
  }
}
