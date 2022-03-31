package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserChallengeUpdateScoreException extends CustomException {

  public UserChallengeUpdateScoreException() {
    super("user_challenge_score_update_error", HttpStatus.NOT_ACCEPTABLE);
    this.setData("user_challenge_score_update_error");
  }


}
