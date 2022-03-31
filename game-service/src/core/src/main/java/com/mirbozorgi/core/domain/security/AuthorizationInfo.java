package com.mirbozorgi.core.domain.security;

public class AuthorizationInfo {

  private long playerId;


  public AuthorizationInfo() {
  }

  public AuthorizationInfo(long playerId) {
    this.playerId = playerId;
  }

  public long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }
}
