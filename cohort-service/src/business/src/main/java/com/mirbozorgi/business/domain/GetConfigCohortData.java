package com.mirbozorgi.business.domain;

public class GetConfigCohortData {

  private String cohortName;

  private Object config;

  public GetConfigCohortData(String cohortName, Object config) {
    this.cohortName = cohortName;
    this.config = config;
  }

  public String getCohortName() {
    return cohortName;
  }

  public Object getConfig() {
    return config;
  }
}
