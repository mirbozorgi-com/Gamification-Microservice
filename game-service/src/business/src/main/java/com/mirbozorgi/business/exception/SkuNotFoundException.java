package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class SkuNotFoundException extends CustomException {


  public SkuNotFoundException() {
    super("sku_not_found", HttpStatus.NOT_FOUND);
    this.setData("sku_not_found");
  }

}
