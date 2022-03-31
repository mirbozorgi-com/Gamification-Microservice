package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class MatchNotFoundException extends CustomException {

  public MatchNotFoundException() {
    super("match_not_exist", HttpStatus.NOT_ACCEPTABLE);
    this.setData("match_not_exist");
  }

}
