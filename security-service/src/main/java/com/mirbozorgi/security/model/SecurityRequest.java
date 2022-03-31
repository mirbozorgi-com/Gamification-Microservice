package com.mirbozorgi.security.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class SecurityRequest {

  @NotBlank
  @Size(min = 8)
  private String password;

  @NotBlank
  @Email
  private String email;


  @NotBlank
  private String globalUniqueId;

  private String deviceId;

  @NotBlank
  @NotNull
  private String marketName;

  @NotBlank
  @NotNull
  private String gamePackageName;

  @NotBlank
  @NotNull
  private String env;

  public String getMarketName() {
    return marketName;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getGlobalUniqueId() {
    return globalUniqueId;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }
}
