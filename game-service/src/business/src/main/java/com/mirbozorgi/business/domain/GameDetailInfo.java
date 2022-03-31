package com.mirbozorgi.business.domain;

public class GameDetailInfo {

  private int id;
  private int gameId;
  private String publicKey;
  private String privateKey;
  private String failMirrorLink;
  private String successMirrorLink;


  public GameDetailInfo(int id, int gameId, String publicKey, String privateKey,
      String failMirrorLink, String successMirrorLink) {
    this.id = id;
    this.gameId = gameId;
    this.publicKey = publicKey;
    this.privateKey = privateKey;
    this.failMirrorLink = failMirrorLink;
    this.successMirrorLink = successMirrorLink;
  }

  public int getId() {
    return id;
  }

  public int getGameId() {
    return gameId;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public String getPrivateKey() {
    return privateKey;
  }

  public String getFailMirrorLink() {
    return failMirrorLink;
  }

  public String getSuccessMirrorLink() {
    return successMirrorLink;
  }
}
