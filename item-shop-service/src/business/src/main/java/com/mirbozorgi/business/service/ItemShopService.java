package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.ItemShopData;
import com.mirbozorgi.business.domain.ItemShopFindAll;
import com.mirbozorgi.core.constant.ItemType;
import com.mirbozorgi.core.domain.ItemData;
import java.util.List;
import mirbozorgi.base.domain.user.WalletChange;

public interface ItemShopService {


  ItemShopData save(
      String name,
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
      List<Integer> avatarPurchaseIds);

  ItemShopData update(
      String name,
      String gamePackageName,
      String env,
      String market,
      Object config,
      List<ItemType> type,
      long vipPeriodTime,
      int gold,
      int gem,
      int level,
      int xp,
      int chance,
      List<Integer> avatarPurchaseIds,
      String fileUrl);


  void delete(String name,
      String gamePackageName,
      String env,
      String market);

  List<ItemShopFindAll> findAll(
      String gamePackageName,
      String env,
      String market);


  ItemData findOne(
      String name,
      String gamePackageName,
      String env,
      String market);

  WalletChange buy(
      String uuid,
      String username,
      String skuName,
      String gamePackageName,
      String env,
      String market,
      String marketToken,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endVipTime
  );

  WalletChange buyMyket(
      String uuid,
      String username,
      String skuName,
      String gamePackageName,
      String env,
      String market,
      String marketToken,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endVipTime
  );

  WalletChange hillaBuy(
      String uuid,
      String username,
      String skuName,
      String gamePackageName,
      String env,
      String market,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endVipTime,
      String orderId,
      String transactionId
  );

  void consumePurchaseMyket(
      String uuid,
      String skuName,
      String gamePackageName,
      String env,
      String market,
      String marketToken
  );

  void consumePurchase(
      String uuid,
      String skuName,
      String gamePackageName,
      String env,
      String market,
      String marketToken
  );

  void consumeHillaPurchase(
      String uuid,
      String skuName,
      String gamePackageName,
      String env,
      String market,
      String orderId,
      String transactionId
  );

}
