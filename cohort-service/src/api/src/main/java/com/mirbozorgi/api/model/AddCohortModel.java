package com.mirbozorgi.api.model;

public class AddCohortModel {


  private String name;
  private int chance;
  private boolean active;
  private String constCohort;
  private String config;
  private String defaultCohort;
  private int minVersionClient;
  private int maxVersionClient;

  public String getName() {
    return name;
  }

  public int getChance() {
    return chance;
  }

  public boolean isActive() {
    return active;
  }

  public String getConstCohort() {
    return constCohort;
  }

  public String getConfig() {
    return config;
  }

  public String getDefaultCohort() {
    return defaultCohort;
  }

  public int getMinVersionClient() {
    return minVersionClient;
  }

  public int getMaxVersionClient() {
    return maxVersionClient;
  }
}
