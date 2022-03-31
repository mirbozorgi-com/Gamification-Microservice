package com.mirbozorgi.business.domain;

public class CohortData {

  private String id;

  private String gamePrivateKey;

  private String name;

  private int chance;

  private boolean active;

  private String constCohort;

  private Object config;

  private String defaultCohort;

  private int minVersionClient;

  private int maxVersionClient;

  public CohortData(String id, String gamePrivateKey, String name, int chance, boolean active,
      String constCohort, Object config, String defaultCohort, int minVersionClient,
      int maxVersionClient) {
    this.id = id;
    this.gamePrivateKey = gamePrivateKey;
    this.name = name;
    this.chance = chance;
    this.active = active;
    this.constCohort = constCohort;
    this.config = config;
    this.defaultCohort = defaultCohort;
    this.minVersionClient = minVersionClient;
    this.maxVersionClient = maxVersionClient;
  }

  public String getId() {
    return id;
  }

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
