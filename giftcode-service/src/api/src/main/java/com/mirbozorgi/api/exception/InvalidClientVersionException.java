package com.mirbozorgi.api.exception;


import mirbozorgi.base.exception.CustomException;

public class InvalidClientVersionException extends CustomException {

  public InvalidClientVersionException() {
    super("invalid_client_version");
  }
}
