package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class EventJoinTimeExcption extends CustomException {

  public EventJoinTimeExcption() {
    super("user_cant_join_the_event", HttpStatus.NOT_ACCEPTABLE);
    this.setData("user_cant_join_the_event");
  }

}