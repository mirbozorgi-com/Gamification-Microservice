package com.mirbozorgi.security.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class EmailVerifiedException extends CustomException {

  public EmailVerifiedException() {

    super("email_has_not_validated_yet!", HttpStatus.NOT_ACCEPTABLE);
  }


}
