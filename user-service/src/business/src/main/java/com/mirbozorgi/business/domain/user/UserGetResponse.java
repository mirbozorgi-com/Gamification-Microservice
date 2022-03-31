package com.mirbozorgi.business.domain.user;

public class UserGetResponse {

  private String uuid;

  public UserGetResponse(String uuid) {
    this.uuid = uuid;
  }

  public String getUuid() {
    return uuid;
  }
}
