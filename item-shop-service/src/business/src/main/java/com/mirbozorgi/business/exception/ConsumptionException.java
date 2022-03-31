package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ConsumptionException extends CustomException {

  public ConsumptionException() {
    super("you_should_consume", HttpStatus.NOT_ACCEPTABLE);
    this.setData("you_should_consume");
  }

}
