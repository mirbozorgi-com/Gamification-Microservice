package com.mirbozorgi.api.model;

public class AddOrUpdateGameModel {

  private String cohortName;
  private boolean test;
  private boolean debug;
  private int clientVersion;
  private String keyForClaimDailyContinueReward;


  public String getKeyForClaimDailyContinueReward() {
    return keyForClaimDailyContinueReward;
  }

  public String getCohortName() {
    return cohortName;
  }

  public boolean isTest() {
    return test;
  }

  public boolean isDebug() {
    return debug;
  }

  public int getClientVersion() {
    return clientVersion;
  }

}
