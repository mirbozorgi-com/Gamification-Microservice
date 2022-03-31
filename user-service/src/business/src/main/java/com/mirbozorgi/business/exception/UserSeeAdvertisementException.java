package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserSeeAdvertisementException extends CustomException {

  public UserSeeAdvertisementException(long secondRemain) {
    super("sooner_request_please_wait!", HttpStatus.FORBIDDEN);
    this.setData(secondRemain + " seconds is remain");
  }
}
