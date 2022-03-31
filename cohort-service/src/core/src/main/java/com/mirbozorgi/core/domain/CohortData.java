package com.mirbozorgi.core.domain;

public class CohortData {

  private String gamePrivateKey;

  private String name;

  private int chance;

  private boolean active;

  private String constCohort;

  private Object config;

  private String defaultCohort;

  private int minVersionClient;

  private int maxVersionClient;

  public String getGamePrivateKey() {
    return gamePrivateKey;
  }

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

  public Object getConfig() {
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
