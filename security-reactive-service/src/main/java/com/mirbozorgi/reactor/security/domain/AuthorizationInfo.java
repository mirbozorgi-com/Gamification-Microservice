package com.mirbozorgi.reactor.security.domain;

public class AuthorizationInfo {

  private String role;
  private String email;
  private String uuid;
  private String deviceId;
  private String globalUniqueId;
  private String sessionId;


  public AuthorizationInfo(
      Object role,
      Object email,
      Object uuid,
      Object deviceId,
      Object globalUniqueId,
      Object sessionId) {
    this.role = role.toString();
    this.email = email.toString();
    this.uuid = uuid.toString();
    this.deviceId = deviceId.toString();
    this.globalUniqueId = globalUniqueId.toString();
    this.sessionId = sessionId.toString();
  }

  public AuthorizationInfo() {
  }

  public String getSessionId() {
    return sessionId;
  }

  public String getRole() {
    return role;
  }

  public String getEmail() {
    return email;
  }

  public String getUuid() {
    return uuid;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public String getGlobalUniqueId() {
    return globalUniqueId;
  }
}
