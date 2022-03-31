package com.mirbozorgi.api.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddTokenFCMModel {

  @NotNull
  @NotEmpty
  private String tokenFCM;

  public String getTokenFCM() {
    return tokenFCM;
  }
}
