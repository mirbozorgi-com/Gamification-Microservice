package com.mirbozorgi.business.domain;

public class ClaimData {

  private Object reward;

  private boolean claim;

  private String challengeId;

  private String userUuId;

  public ClaimData(Object reward, boolean claim, String challengeId,
      String userUuId) {
    this.reward = reward;
    this.claim = claim;
    this.challengeId = challengeId;
    this.userUuId = userUuId;
  }

  public Object getReward() {
    return reward;
  }

  public boolean isClaim() {
    return claim;
  }

  public String getChallengeId() {
    return challengeId;
  }

  public String getUserUuId() {
    return userUuId;
  }
}
