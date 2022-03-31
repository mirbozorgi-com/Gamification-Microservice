package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserReferalUsedException extends CustomException {

  public UserReferalUsedException() {
    super("user_referral_used_before", HttpStatus.FORBIDDEN);
    this.setData("user_referral_used_before");
  }
}
