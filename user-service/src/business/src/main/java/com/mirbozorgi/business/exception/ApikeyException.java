package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ApikeyException extends CustomException {

  public ApikeyException() {
    super("api_key_wrong", HttpStatus.FORBIDDEN);
    this.setData("api_key_wrong");
  }
}
