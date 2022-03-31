package com.mirbozorgi.security.repository.entity;

import com.mirbozorgi.security.entity.User;
import com.mirbozorgi.security.constant.EnumRole;
import java.util.List;

public interface SecurityRepository {

  User register(User user);

  boolean updateGuestToUser(
      String uuid,
      String password,
      String email,
      String globalUniqueId,
      String deviceId,
      EnumRole role,
      String verificationCode,
      String sessionId,
      String gamePackageName,
      String env);

  List<User> get(
      String email,
      String deviceId,
      String gamePackageName,
      String env);

  List<User> getWithRoleAndDeviceAndEmail(
      String email,
      String deviceId,
      String gamePackageName,
      String env,
      EnumRole role);


  String getDeviceId(String uuid,
      String gamePackageName,
      String env,
      String market);


  void updateSessionId(
      String uuid,
      String gamePackageName,
      String market,
      String env,
      String sessionId);

  void updateEmail(String deviceId,
      String gamePackageName,
      String env,
      String market,
      String email,
      Boolean verifyEmail);


  boolean updateGlobalAndDeviceId(
      String email,
      String globalUniqueId,
      String deviceId,
      String gamePackageName,
      String env,
      String market);


  void verify(String email, Boolean verifyEmail, long verifiedDate);


  void changePassword(String email, String password);


  void regenerateVerificationCode(String email, String verificationCode,
      long verificationCodeCreatedDate);


  void sendForgetPassToken(
      String email,
      long createdForgetPassTokenDate,
      String forgetPassToken);


  User getByEmail(
      String email,
      String gamePackageName,
      String env,
      String market);

  User getByEmailInAllMarket(
      String email,
      String gamePackageName,
      String env);

  User getByUuId(String uuid,
      String gamePackageName,
      String market,
      String env);


  User getByRoleAndDeviceId(
      EnumRole role,
      String deviceId,
      String gamePackageName,
      String env,
      String market);

  User getByRoleAndDeviceIdWithEmailAndNotVerified(
      EnumRole role,
      Boolean verifyEmail,
      String deviceId,
      String gamePackageName,
      String env,
      String market);


}
