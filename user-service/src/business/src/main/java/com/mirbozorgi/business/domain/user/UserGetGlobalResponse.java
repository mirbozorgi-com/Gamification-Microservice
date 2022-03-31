package com.mirbozorgi.business.domain.user;


import mirbozorgi.base.constanct.PlayerType;

public class UserGetGlobalResponse {

  private String uuid;

  private int clientVersion;

  private String username;

  private String tokenFCM;

  private PlayerType playerType;

  public UserGetGlobalResponse(String uuid, int clientVersion, String username,
      PlayerType playerType,
      String tokenFCM) {
    this.uuid = uuid;
    this.clientVersion = clientVersion;
    this.username = username;
    this.playerType = playerType;
    this.tokenFCM = tokenFCM;
  }

  public UserGetGlobalResponse() {
  }

  public String getTokenFCM() {
    return tokenFCM;
  }

  public PlayerType getPlayerType() {
    return playerType;
  }

  public String getUsername() {
    return username;
  }

  public String getUuid() {
    return uuid;
  }

  public int getClientVersion() {
    return clientVersion;
  }
}
