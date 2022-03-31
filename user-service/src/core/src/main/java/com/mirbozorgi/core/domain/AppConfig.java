package com.mirbozorgi.core.domain;

public class AppConfig {

  private Integer clientVersion;

  private boolean maintenanceMode;

  private Integer[] cohortChance;

  public AppConfig() {

    clientVersion = 0;
    cohortChance = new Integer[]{25, 25, 25, 25};
    maintenanceMode = true;
  }

  public Integer getClientVersion() {
    return clientVersion;
  }

  public void setClientVersion(Integer clientVersion) {
    this.clientVersion = clientVersion;
  }

  public boolean isMaintenanceMode() {
    return maintenanceMode;
  }

  public void setMaintenanceMode(boolean maintenanceMode) {
    this.maintenanceMode = maintenanceMode;
  }

  public Integer[] getCohortChance() {
    return cohortChance;
  }

  public void setCohortChance(Integer[] cohortChance) {
    this.cohortChance = cohortChance;
  }
}
