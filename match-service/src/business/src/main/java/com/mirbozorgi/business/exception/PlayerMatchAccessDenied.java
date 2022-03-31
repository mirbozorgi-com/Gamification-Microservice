package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class PlayerMatchAccessDenied extends CustomException {

  public PlayerMatchAccessDenied() {
    super("player_is_not_for_this_match", HttpStatus.FORBIDDEN);
    this.setData("player_is_not_for_this_match");
  }
}
