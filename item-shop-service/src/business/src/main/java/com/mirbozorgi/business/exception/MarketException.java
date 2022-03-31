package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class MarketException extends CustomException {

  public MarketException() {
    super("market_is_not_for_this_game", HttpStatus.NOT_ACCEPTABLE);
    this.setData("market_is_not_for_this_game");
  }

}
