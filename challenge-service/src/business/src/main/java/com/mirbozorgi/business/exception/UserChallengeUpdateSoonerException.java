package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserChallengeUpdateSoonerException extends CustomException {

  public UserChallengeUpdateSoonerException() {
    super("user_challenge_update_score_sooner_than_config", HttpStatus.NOT_ACCEPTABLE);
    this.setData("user_challenge_update_score_sooner_than_config");
  }

}
