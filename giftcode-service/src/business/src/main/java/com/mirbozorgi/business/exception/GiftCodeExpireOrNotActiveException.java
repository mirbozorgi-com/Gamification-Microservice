package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;


public class GiftCodeExpireOrNotActiveException extends CustomException {


  public GiftCodeExpireOrNotActiveException() {
    super("gift_code_expired_or_not_active", HttpStatus.NOT_ACCEPTABLE);
    this.setData("gift_code_expired_or_not_active");
  }

}
