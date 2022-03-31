package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.GiftCodeUseData;
import java.util.List;

public interface UsedGiftCodeService {

  GiftCodeUseData add(
      String userUuId,
      String code,
      String gamePackageName,
      String env,
      String marketName,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endVipTime);


  GiftCodeUseData getBy(
      String userUuId,
      String code,
      String gamePackageName,
      String env,
      String marketName);


  List<GiftCodeUseData> getAll(
      String code,
      String gamePackageName,
      String env,
      String marketName,
      String userUuId);
}
