package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ChallengeActiveException extends CustomException {


  public ChallengeActiveException() {
    super("challenge_not_active", HttpStatus.NOT_ACCEPTABLE);
    this.setData("challenge_not_active");
  }
}
