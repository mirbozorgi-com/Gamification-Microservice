package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserClientVersionException extends CustomException {

  public UserClientVersionException() {
    super("user_client_version_is_not_valid_for_this_challenge", HttpStatus.NOT_ACCEPTABLE);
    this.setData("user_client_version_is_not_valid_for_this_challenge");
  }

}
