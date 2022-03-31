package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UseGiftCodeException extends CustomException {

  public UseGiftCodeException() {
    super("gift_code_credentials_is_wrong", HttpStatus.NOT_ACCEPTABLE);
    this.setData("gift_code_credentials_is_wrong");
  }

}
