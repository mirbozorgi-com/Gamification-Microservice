package com.mirbozorgi.business.exception.event;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class JoinLateException extends CustomException {

  public JoinLateException() {
    super("event_join_time_is_expired", HttpStatus.NOT_ACCEPTABLE);
    this.setData("event_join_time_is_expired");
  }
}
