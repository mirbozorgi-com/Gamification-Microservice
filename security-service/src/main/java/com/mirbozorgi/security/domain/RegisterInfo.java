package com.mirbozorgi.security.domain;

import com.mirbozorgi.security.constant.EnumRole;

public class RegisterInfo {

  private String uuid;

  private EnumRole role;

  private String email;

  private String globalUniqueId;

  private String deviceId;


  public RegisterInfo(String uuid, EnumRole role, String email, String globalUniqueId,
      String deviceId) {
    this.uuid = uuid;
    this.role = role;
    this.email = email;
    this.globalUniqueId = globalUniqueId;
    this.deviceId = deviceId;
  }

  public String getGlobalUniqueId() {
    return globalUniqueId;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public String getUuid() {
    return uuid;
  }

  public EnumRole getRole() {
    return role;
  }

  public String getEmail() {
    return email;
  }
}
