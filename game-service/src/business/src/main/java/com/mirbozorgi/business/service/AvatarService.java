package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.AvatarInfo;
import com.mirbozorgi.core.utilities.AvatarBuyType;
import com.mirbozorgi.core.utilities.CurrencyType;
import java.math.BigDecimal;
import java.util.List;

public interface AvatarService {

  AvatarInfo save(
      int gameId,
      String name,
      BigDecimal amount,
      CurrencyType currencyType,
      Boolean isActive,
      String avatarType,
      Boolean free,
      AvatarBuyType typeForBuy);

  AvatarInfo findById(long id);

  AvatarInfo findByNameAndGameId(String name, int gameId);

  List<AvatarInfo> findAll(String gamePackageName, String env);

  void delete(int id);

  AvatarInfo update(
      int id,
      String name,
      BigDecimal amount,
      CurrencyType currencyType,
      Boolean isActive,
      String avatarType,
      Boolean free,
      AvatarBuyType typeForBuy);
}
