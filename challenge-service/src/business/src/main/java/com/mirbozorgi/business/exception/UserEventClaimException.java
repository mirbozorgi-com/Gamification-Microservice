package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserEventClaimException extends CustomException {

  public UserEventClaimException() {
    super("user_event_not_finished_yet", HttpStatus.UNAUTHORIZED);
  }

}