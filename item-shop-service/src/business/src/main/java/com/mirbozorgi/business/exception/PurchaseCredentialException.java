package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class PurchaseCredentialException extends CustomException {

  public PurchaseCredentialException() {
    super("purchase_credential_is_not_valid", HttpStatus.FORBIDDEN);
    this.setData("purchase_credential_is_not_valid");
  }

}
