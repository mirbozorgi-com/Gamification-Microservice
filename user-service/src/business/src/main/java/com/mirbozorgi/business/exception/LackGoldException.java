package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class LackGoldException extends CustomException {

  public LackGoldException() {
    super("lack_of_gold", HttpStatus.NOT_ACCEPTABLE);
    this.setData("lack_of_gold");
  }
}
