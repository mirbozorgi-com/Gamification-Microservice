package com.mirbozorgi.business.domain;

public class IncScoreResponse {

  private int score;
  private String challengeId;
  private String userUuId;
  private String gamePackageName;
  private String env;
  private String marketName;


  public IncScoreResponse(int score, String challengeId, String userUuId,
      String gamePackageName, String env, String marketName) {
    this.score = score;
    this.challengeId = challengeId;
    this.userUuId = userUuId;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
  }

  public int getScore() {
    return score;
  }

  public String getChallengeId() {
    return challengeId;
  }

  public String getUserUuId() {
    return userUuId;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getMarketName() {
    return marketName;
  }
}
