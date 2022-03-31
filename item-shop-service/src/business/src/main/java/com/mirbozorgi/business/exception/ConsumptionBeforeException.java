package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ConsumptionBeforeException extends CustomException {

  public ConsumptionBeforeException() {
    super("you_buy_or_consume_before", HttpStatus.NOT_ACCEPTABLE);
    this.setData("you_buy_or_consume_before");
  }


}
