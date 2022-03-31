package com.mirbozorgi.api.exception;

import mirbozorgi.base.exception.CustomException;

public class InvalidMarketException extends CustomException {

  public InvalidMarketException() {
    super("invalid_market");
  }
}
