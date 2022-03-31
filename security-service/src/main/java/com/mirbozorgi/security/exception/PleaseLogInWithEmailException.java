package com.mirbozorgi.security.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class PleaseLogInWithEmailException extends CustomException {


  public PleaseLogInWithEmailException() {

    super("please_login_with_email_you_are_not_guest", HttpStatus.NOT_ACCEPTABLE);
  }
}
