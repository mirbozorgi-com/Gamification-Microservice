package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class TokenParsForUserServiceException extends CustomException {
  public TokenParsForUserServiceException() {
    super("token_parser_user_service_exception", HttpStatus.NOT_ACCEPTABLE);
    this.setData("token_parser_user_service_exception");
  }
}
