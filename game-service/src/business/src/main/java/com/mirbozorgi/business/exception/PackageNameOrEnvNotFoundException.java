package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class PackageNameOrEnvNotFoundException extends CustomException {

  public PackageNameOrEnvNotFoundException() {
    super("packageName_or_env_not_found", HttpStatus.NOT_FOUND);
    this.setData("packageName_or_env_not_found");
  }

}
