package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class LackXpException extends CustomException {

  public LackXpException() {
    super("lack_of_xp", HttpStatus.NOT_ACCEPTABLE);
    this.setData("lack_of_xp");
  }

}
