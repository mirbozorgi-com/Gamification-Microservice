package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class PlayerHasActiveMatchException extends CustomException {

  public PlayerHasActiveMatchException() {
    super("player_has_active_match_already", HttpStatus.NOT_ACCEPTABLE);
    this.setData("player_has_active_match_already");
  }

}
