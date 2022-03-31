package com.mirbozorgi.business.exception.event;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserEventNotFoundException extends CustomException {

  public UserEventNotFoundException() {
    super("user_event_not_found", HttpStatus.NOT_ACCEPTABLE);
    this.setData("user_event_not_found");
  }
}
