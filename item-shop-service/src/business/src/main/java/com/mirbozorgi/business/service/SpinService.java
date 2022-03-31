package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.SpinInfo;
import com.mirbozorgi.business.domain.SpinRollingInfo;
import com.mirbozorgi.core.constant.ItemType;
import com.mirbozorgi.core.domain.ItemData;
import com.mirbozorgi.core.domain.SpinData;
import com.mirbozorgi.core.domain.UserLastSpinData;
import java.util.List;

public interface SpinService {

  SpinInfo addOrUpdate(
      String gamePackageName,
      String env,
      String market,
      String key,
      long periodTime);

  void addItem(
      String key,
      String gamePackageName,
      String env,
      String market,
      Object config,
      List<ItemType> types,
      long vipPeriodTime,
      String fileUrl,
      int xp,
      int gold,
      int gem,
      Short level,
      List<Integer> avatarPurchaseIds,
      int chance
  );

  List<String> getKeys(
      String gamePackageName,
      String env,
      String market
  );


  SpinData getAllByKey(
      String gamePackageName,
      String env,
      String market,
      String key
  );

  SpinRollingInfo rolling(
      String uuid,
      String name,
      String username,
      String gamePackageName,
      String env,
      String market,
      String key,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endVipTime
  );

  UserLastSpinData getAllUserSpin(
      String uuid,
      String gamePackageName,
      String env,
      String market,
      String key);


  SpinInfo updateItems(
      String key,
      String gamePackageName,
      String env,
      String market,
      List<ItemData> itemDatas
  );
}
