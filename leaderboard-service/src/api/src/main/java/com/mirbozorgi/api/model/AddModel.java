package com.mirbozorgi.api.model;

public class AddModel {


  private String challengeId;
  private String username;
  private Boolean allMarketInclude;

  public Boolean getAllMarketInclude() {
    return allMarketInclude;
  }


  public String getChallengeId() {
    return challengeId;
  }

  public String getUsername() {
    return username;
  }
}
