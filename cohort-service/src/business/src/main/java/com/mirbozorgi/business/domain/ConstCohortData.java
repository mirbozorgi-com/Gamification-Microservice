package com.mirbozorgi.business.domain;

public class ConstCohortData {

  private String id;

  private String gamePackageName;

  private String name;

  private Object config;

  public ConstCohortData(String id, String gamePackageName, String name, Object config) {
    this.id = id;
    this.gamePackageName = gamePackageName;
    this.name = name;
    this.config = config;
  }

  public String getId() {
    return id;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getName() {
    return name;
  }

  public Object getConfig() {
    return config;
  }
}
