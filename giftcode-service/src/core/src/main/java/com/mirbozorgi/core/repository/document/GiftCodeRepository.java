package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.docuemnt.GiftCode;
import java.util.List;
import mirbozorgi.base.domain.user.WalletChange;


public interface GiftCodeRepository {

  GiftCode save(GiftCode giftCode);

  void delete(GiftCode giftCode);

  GiftCode findById(String id);

  List<GiftCode> findAll(
      String name,
      String code,
      String gamePackageName,
      String env,
      String marketName);

  GiftCode findBy(String code, String gamePackageName, String marketName, String env);

  GiftCode update(
      String id,
      String name,
      String code,
      String gamePackageName,
      String marketName,
      String env,
      WalletChange walletChange,
      long expireDate,
      int maxUseCountGlobal,
      boolean isActive,
      int minClientVersion,
      int maxClientVersion,
      int minLevelPermission,
      int daysAfterInstall);

  void useGiftCode(
      String code,
      String gamePackageName,
      String marketName,
      String env);
}
