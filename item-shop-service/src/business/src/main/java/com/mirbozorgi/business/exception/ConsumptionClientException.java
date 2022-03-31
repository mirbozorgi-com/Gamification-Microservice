package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ConsumptionClientException extends CustomException {

  public ConsumptionClientException() {
    super("hey_client_you_should_consume_first", HttpStatus.NOT_ACCEPTABLE);
    this.setData("hey_client_you_should_consume_first");
  }

}
