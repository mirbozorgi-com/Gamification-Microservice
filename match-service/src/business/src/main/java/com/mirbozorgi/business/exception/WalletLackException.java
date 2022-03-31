package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class WalletLackException extends CustomException {

  public WalletLackException() {
    super("wallet_lack_of_gem_gold", HttpStatus.NOT_ACCEPTABLE);
    this.setData("wallet_lack_of_gem_gold");
  }

}
