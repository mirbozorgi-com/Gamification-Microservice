package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserHasAvatarException extends CustomException {

  public UserHasAvatarException() {
    super("user_had_avatar_before", HttpStatus.NOT_ACCEPTABLE);
    this.setData("user_had_avatar_before");
  }
}
