package com.mirbozorgi.business.exception;


import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class GiftCodeUsedException extends CustomException {

  public GiftCodeUsedException() {
    super("gift_code_used_before", HttpStatus.NOT_ACCEPTABLE);
    this.setData("gift_code_used_before");
  }

}