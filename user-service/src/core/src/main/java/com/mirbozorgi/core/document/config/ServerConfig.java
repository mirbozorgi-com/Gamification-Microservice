package com.mirbozorgi.core.document.config;

public class ServerConfig {

  private Integer minSupportedClientVersion;

  private boolean enableMaintenanceInterceptor;

  private boolean enableApiUsageInterceptor;

  private boolean enableHmacInterceptor;

  private boolean enableLogBackInterceptor;

  public ServerConfig() {

    this.minSupportedClientVersion = 0;
    this.enableMaintenanceInterceptor = true;
    this.enableApiUsageInterceptor = true;
    this.enableHmacInterceptor = true;
    this.enableLogBackInterceptor = true;
  }

  public boolean isEnableMaintenanceInterceptor() {
    return enableMaintenanceInterceptor;
  }

  public Integer getMinSupportedClientVersion() {
    return minSupportedClientVersion;
  }

  public boolean isEnableApiUsageInterceptor() {
    return enableApiUsageInterceptor;
  }

  public boolean isEnableHmacInterceptor() {
    return enableHmacInterceptor;
  }

  public boolean isEnableLogBackInterceptor() {
    return enableLogBackInterceptor;
  }
}
