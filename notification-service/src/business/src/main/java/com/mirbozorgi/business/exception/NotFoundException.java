package com.mirbozorgi.business.exception;


import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;


public class NotFoundException extends CustomException {

  public NotFoundException() {
    super("not_found", HttpStatus.NOT_FOUND);
    this.setData("not_found");
  }
}
