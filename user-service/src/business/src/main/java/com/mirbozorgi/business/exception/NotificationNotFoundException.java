package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class NotificationNotFoundException extends CustomException {

  public NotificationNotFoundException() {
    super("notification_not_found", HttpStatus.NOT_FOUND);
    this.setData("notification_not_found");
  }
}
