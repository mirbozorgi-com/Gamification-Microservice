package com.mirbozorgi.security.service;

import com.mirbozorgi.security.domain.AuthorizationInfo;
import com.mirbozorgi.security.domain.LoginInfo;
import com.mirbozorgi.security.domain.RegisterInfo;
import mirbozorgi.base.domain.security.GetByEmailInfo;
import mirbozorgi.base.domain.security.GetDeviceIdInfo;

public interface SecurityService {

  RegisterInfo userRegister(
      String email,
      String password,
      String deviceId,
      String globalUniqueId,
      String uuid,
      String gamePackageName,
      String env,
      String marketName);

  LoginInfo login(
      String email,
      String password,
      String globalUniqueId,
      String deviceId,
      String gamePackageName,
      String env,
      String marketName);

  GetDeviceIdInfo getDeviceId(String uuid,
      String gamePackageName,
      String env,
      String marketName);

  GetByEmailInfo getBy(
      String email,
      String uuid,
      String gamePackageName,
      String env,
      String marketName);

  AuthorizationInfo authorize(
      String token,
      String gamePackageName,
      String env,
      String marketName);

  void verify(
      String email,
      String gamePackageName,
      String env,
      String marketName,
      String verificationCode);


  void reGenerateVerificationCode(
      String email,
      String gamePackageName,
      String env,
      String marketName);

  void changePassword(
      String email,
      String gamePackageName,
      String env,
      String marketName,
      String password,
      String newPassword);

  void forgetPassword(
      String email,
      String gamePackageName,
      String env,
      String marketName,
      String password,
      String forgetPasswordToken);

  void sendForgetPassToken(
      String email,
      String gamePackageName,
      String env,
      String marketName);


}
