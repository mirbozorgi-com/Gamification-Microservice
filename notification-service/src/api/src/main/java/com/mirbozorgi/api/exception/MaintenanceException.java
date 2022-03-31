package com.mirbozorgi.api.exception;


import mirbozorgi.base.exception.CustomException;

public class MaintenanceException extends CustomException {

  public MaintenanceException() {
    super("maintenance_mode");
  }

  public MaintenanceException(String msg) {
    this();
    this.setMsg(msg);
  }
}
