package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class LackGemException extends CustomException {

  public LackGemException() {
    super("lack_of_gem", HttpStatus.NOT_ACCEPTABLE);
    this.setData("lack_of_gem");
  }

}
