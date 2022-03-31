package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.GiftCodeInfo;
import java.util.List;
import mirbozorgi.base.domain.user.WalletChange;

public interface GiftCodeService {

  GiftCodeInfo save(
      String name,
      String code,
      String gamePackageName,
      String env,
      String marketName,
      WalletChange walletChange,
      long expireDate,
      int maxUseCountGlobal,
      boolean isActive,
      int minClientVersion,
      int maxClientVersion,
      int minLevelPermission,
      int daysAfterInstall);

  void delete(String id);

  GiftCodeInfo findById(String id);

  List<GiftCodeInfo> findAll(
      String name,
      String code,
      String gamePackageName,
      String env,
      String marketName);

  GiftCodeInfo findBy(String code, String gamePackageName, String marketName, String env);

  GiftCodeInfo update(
      String id,
      String name,
      String code,
      String gamePackageName,
      String env,
      String marketName,
      WalletChange walletChange,
      long expireDate,
      int maxUseCountGlobal,
      boolean isActive,
      int minClientVersion,
      int maxClientVersion,
      int minLevelPermission,
      int daysAfterInstall);

  void useGiftCode(
      Integer clientVersion,
      Integer level,
      Integer daysAfterInstall,
      String code,
      String env,
      String gamePackageName,
      String marketName);

}
