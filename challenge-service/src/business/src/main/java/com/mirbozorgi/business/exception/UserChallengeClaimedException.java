package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserChallengeClaimedException extends CustomException {

  public UserChallengeClaimedException() {
    super("user_challenge_claimed_before", HttpStatus.NOT_ACCEPTABLE);
    this.setData("user_challenge_claimed_before");
  }

}
