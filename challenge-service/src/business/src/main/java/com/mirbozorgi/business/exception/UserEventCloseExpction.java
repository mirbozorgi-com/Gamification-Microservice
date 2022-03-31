package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserEventCloseExpction extends CustomException {

  public UserEventCloseExpction() {
    super("user_event_not_terminated_yet", HttpStatus.UNAUTHORIZED);
  }

}