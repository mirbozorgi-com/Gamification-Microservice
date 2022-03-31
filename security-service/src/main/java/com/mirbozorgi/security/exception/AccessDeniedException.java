package com.mirbozorgi.security.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class AccessDeniedException extends CustomException {

  public AccessDeniedException() {
    super("access_denied", HttpStatus.FORBIDDEN);
  }

}
