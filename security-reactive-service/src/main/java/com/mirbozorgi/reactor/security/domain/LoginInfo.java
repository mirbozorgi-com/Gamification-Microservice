package com.mirbozorgi.reactor.security.domain;

public class LoginInfo {

  private String token;
  private String uuid;

  public LoginInfo(String token, String uuid) {
    this.token = token;
    this.uuid = uuid;
  }

  public String getUuid() {
    return uuid;
  }

  public String getToken() {
    return token;
  }
}
