package com.mirbozorgi.security.entity;

import com.mirbozorgi.security.constant.EnumRole;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_security",
    uniqueConstraints = {
    // it's wrong because we cant add several emails for one user !!
//        @UniqueConstraint(name = "UK_ROLE_DEVICE_ID_GAME_PACKAGE_ENV_MARKET",
//            columnNames = {"role", "device_id", "game_package_name", "env", "market"}),
        @UniqueConstraint(name = "UK_USER_EMAIL_GAME_ENV_MARKET", columnNames = {
            "email", "game_package_name", "env", "market"}),

    }

)

public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column(name = "uuid")
  private String uuid;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private EnumRole role;

  @Column(name = "password")
  private String password;

  @Column(name = "session_id")
  private String sessionId;

  @Column(name = "game_package_name", nullable = false)
  private String gamePackageName;

  @Column(name = "env", nullable = false)
  private String env;

  @Column(name = "market", nullable = false)
  private String market;

  @Column(name = "email")
  private String email;


  @Column(name = "global_unique_id")
  private String globalUniqueId;

  @Column(name = "created_date")
  private long createdDate;

  @Column(name = "device_id")
  private String deviceId;

  @Column(name = "verify_email")
  private Boolean verifyEmail;

  @Column(name = "verification_code")
  private String verificationCode;


  @Column(name = "verification_code_created_date")
  private long verificationCodeCreatedDate;


  @Column(name = "forget_pass_token")
  private String forgetPassToken;


  @Column(name = "created_forget_pass_token_date")
  private long createdForgetPassTokenDate;


  @Column(name = "verified_date")
  private long verifiedDate;

  public User(
      String uuid,
      EnumRole role,
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
      String market
  ) {
    this.uuid = uuid;
    this.role = role;
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
  }

  public User() {
  }

  //contractor for creating token
  public User(
      String uuid,
      EnumRole role,
      String email,
      String globalUniqueId,
      String deviceId,
      String sessionId) {
    this.uuid = uuid;
    this.role = role;
    this.email = email;
    this.globalUniqueId = globalUniqueId;
    this.deviceId = deviceId;
    this.sessionId = sessionId;
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

  public EnumRole getRole() {
    return role;
  }

  public long getId() {
    return id;
  }

  public String getUuid() {
    return uuid;
  }


}
