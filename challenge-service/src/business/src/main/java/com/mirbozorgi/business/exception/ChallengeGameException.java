package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ChallengeGameException extends CustomException {

  public ChallengeGameException() {
    super("challenge_is_not_for_this_game", HttpStatus.NOT_ACCEPTABLE);
    this.setData("challenge_is_not_for_this_game");
  }
}
