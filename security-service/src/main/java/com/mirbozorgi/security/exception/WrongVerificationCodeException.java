package com.mirbozorgi.security.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class WrongVerificationCodeException extends CustomException {


  public WrongVerificationCodeException() {

    super("verification_code_wrong_or_expired", HttpStatus.NOT_ACCEPTABLE);
  }

}
