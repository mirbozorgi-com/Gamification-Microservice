package com.mirbozorgi.security.model;

import com.sun.istack.NotNull;
import javax.validation.constraints.NotEmpty;

public class LoginRequestModel {

  @NotNull
  @NotEmpty
  private String deviceId;

  @NotNull
  @NotEmpty
  private String gamePackageName;

  @NotNull
  @NotEmpty
  private String env;

  @NotNull
  @NotEmpty
  private String marketName;

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getMarketName() {
    return marketName;
  }

  public String getDeviceId() {
    return deviceId;
  }
}
