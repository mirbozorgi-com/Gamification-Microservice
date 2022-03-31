package com.mirbozorgi.api.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;


public class ForceUpdateException extends CustomException {

  public ForceUpdateException() {
    super("force_update");
  }

  public ForceUpdateException(String updateLink) {
    super("force_update", HttpStatus.BAD_REQUEST, updateLink);
  }
}
