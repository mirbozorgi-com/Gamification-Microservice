package com.mirbozorgi.api.model;

import javax.validation.constraints.NotBlank;

public class JoinModel {


  @NotBlank
  private String matchName;

  private String username;

  private Object config;


  public String getMatchName() {
    return matchName;
  }

  public String getUsername() {
    return username;
  }

  public Object getConfig() {
    return config;
  }
}
