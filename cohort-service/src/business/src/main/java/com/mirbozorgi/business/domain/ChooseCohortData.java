package com.mirbozorgi.business.domain;

public class ChooseCohortData {

  private String cohortName;

  private String gamePackageName;

  private String market;


  public ChooseCohortData(String cohortName, String gamePackageName, String market) {
    this.cohortName = cohortName;
    this.gamePackageName = gamePackageName;
    this.market = market;
  }

  public String getCohortName() {
    return cohortName;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getMarket() {
    return market;
  }
}
