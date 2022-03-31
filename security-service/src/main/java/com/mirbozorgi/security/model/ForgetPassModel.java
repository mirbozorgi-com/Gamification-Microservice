package com.mirbozorgi.security.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class ForgetPassModel {

  @NotBlank
  @Email
  private String email;

  @NotBlank
  @Size(min = 6, max = 7)
  private String forgetPassToken;

  @NotBlank
  @Size(min = 8)
  private String newPassword;

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

  public String getEmail() {
    return email;
  }

  public String getForgetPassToken() {
    return forgetPassToken;
  }

  public String getNewPassword() {
    return newPassword;
  }
}
