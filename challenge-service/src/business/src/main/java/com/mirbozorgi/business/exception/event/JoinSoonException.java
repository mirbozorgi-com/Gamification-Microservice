package com.mirbozorgi.business.exception.event;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class JoinSoonException extends CustomException {

  public JoinSoonException() {
    super("event_join_time_is_later", HttpStatus.NOT_ACCEPTABLE);
    this.setData("event_join_time_is_later");
  }
}
