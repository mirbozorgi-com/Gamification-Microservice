package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class VipTypeException extends CustomException {

  public VipTypeException() {
    super("you_are_not_vip", HttpStatus.NOT_ACCEPTABLE);
    this.setData("you_are_not_vip");
  }

}
