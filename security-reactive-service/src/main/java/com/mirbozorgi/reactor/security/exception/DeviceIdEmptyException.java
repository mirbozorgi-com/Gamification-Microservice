package com.mirbozorgi.reactor.security.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class DeviceIdEmptyException extends CustomException {

  public DeviceIdEmptyException() {

    super("device_id_should_not_be_empty", HttpStatus.NOT_ACCEPTABLE);
  }

}
