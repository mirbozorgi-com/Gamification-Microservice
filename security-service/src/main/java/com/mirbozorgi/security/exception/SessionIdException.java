package com.mirbozorgi.security.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class SessionIdException extends CustomException {

  public SessionIdException() {
    super("token_expired", HttpStatus.FORBIDDEN);
    this.setData("please_login!");
  }
}
