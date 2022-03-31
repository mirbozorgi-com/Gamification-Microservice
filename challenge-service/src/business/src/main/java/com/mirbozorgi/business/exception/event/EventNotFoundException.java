package com.mirbozorgi.business.exception.event;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class EventNotFoundException extends CustomException {

  public EventNotFoundException() {
    super("event_timing_not_found", HttpStatus.NOT_ACCEPTABLE);
    this.setData("event_timing_not_found");
  }
}
