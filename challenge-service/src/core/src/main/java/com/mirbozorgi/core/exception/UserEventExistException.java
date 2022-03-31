package com.mirbozorgi.core.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserEventExistException extends CustomException {

  public UserEventExistException() {
    super("user_had_this_event_before", HttpStatus.NOT_ACCEPTABLE);
    this.setData("user_had_this_event_before");
  }
}
