package com.mirbozorgi.api.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;


public class UnAuthorizedException extends CustomException {

  public UnAuthorizedException() {
    super("unauthorized", HttpStatus.UNAUTHORIZED);
  }

}
