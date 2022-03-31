package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class SpinSoonerThanConfigException extends CustomException {

  public SpinSoonerThanConfigException() {
    super("spin_sooner_than_config", HttpStatus.FORBIDDEN);
    this.setData("spin_sooner_than_config");
  }

}
