package com.mirbozorgi.reactor.security.entity;

import com.mirbozorgi.reactor.security.constant.EnumRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("user_security")

public class User {

  @Id
  private long id;

  private String uuid;

  private EnumRole role;

  private String password;

  private String sessionId;

  private String gamePackageName;

  private String env;

  private String market;

  private String email;

  private String globalUniqueId;

  private long createdDate;

  private String deviceId;

  private Boolean verifyEmail;

  private String verificationCode;

  private long verificationCodeCreatedDate;

  private String forgetPassToken;

  private long createdForgetPassTokenDate;

  private long verifiedDate;

  public User(
      String uuid,
      String password,
      String email,
      String globalUniqueId,
      String deviceId,
      Boolean verifyEmail,
      String verificationCode,
      String forgetPassToken,
      long createdForgetPassTokenDate,
      long verifiedDate,
      long verificationCodeCreatedDate,
      long createdDate,
      String sessionId,
      String gamePackageName,
      String env,
      String market,
      EnumRole role
  ) {
    this.uuid = uuid;
    this.password = password;
    this.email = email;
    this.globalUniqueId = globalUniqueId;
    this.deviceId = deviceId;
    this.verifyEmail = verifyEmail;
    this.verificationCode = verificationCode;
    this.forgetPassToken = forgetPassToken;
    this.createdForgetPassTokenDate = createdForgetPassTokenDate;
    this.verifiedDate = verifiedDate;
    this.verificationCodeCreatedDate = verificationCodeCreatedDate;
    this.createdDate = createdDate;
    this.sessionId = sessionId;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.market = market;
    this.role = role;
  }

  public User() {
  }

  //contractor for creating token
  public User(
      String uuid,
      String email,
      String globalUniqueId,
      String deviceId,
      EnumRole role,
      String sessionId) {
    this.uuid = uuid;
    this.email = email;
    this.globalUniqueId = globalUniqueId;
    this.deviceId = deviceId;
    this.sessionId = sessionId;
    this.role = role;
  }


  public EnumRole getRole() {
    return role;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getMarket() {
    return market;
  }

  public String getSessionId() {
    return sessionId;
  }

  public long getCreatedDate() {
    return createdDate;
  }

  public long getVerificationCodeCreatedDate() {
    return verificationCodeCreatedDate;
  }

  public String getForgetPassToken() {
    return forgetPassToken;
  }

  public long getCreatedForgetPassTokenDate() {
    return createdForgetPassTokenDate;
  }

  public String getVerificationCode() {
    return verificationCode;
  }

  public long getVerifiedDate() {
    return verifiedDate;
  }

  public Boolean getVerifyEmail() {
    return verifyEmail;
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

  public long getId() {
    return id;
  }

  public String getUuid() {
    return uuid;
  }


}
