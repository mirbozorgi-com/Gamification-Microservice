package com.mirbozorgi.security.service;

import com.mirbozorgi.security.domain.LoginInfo;

public interface GuestService {

  LoginInfo login(
      String deviceId,
      String gamePackageName,
      String env,
      String marketName);

}
