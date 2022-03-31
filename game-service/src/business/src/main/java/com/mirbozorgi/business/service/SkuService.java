package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.SkuInfo;
import com.mirbozorgi.core.utilities.CurrencyType;
import java.math.BigDecimal;
import java.util.List;

public interface SkuService {


  SkuInfo get(int id);

  SkuInfo getByNameAndGameId(int gameId, String name);

  SkuInfo update(
      int id,
      int gameId,
      String name,
      String description,
      BigDecimal amount,
      CurrencyType currencyType,
      Boolean active,
      boolean consumable);

  SkuInfo save(
      int gameId,
      String name,
      String description,
      BigDecimal amount,
      CurrencyType currencyType,
      Boolean active,
      boolean consumable);

  List<SkuInfo> getAll(Integer gameId);

}

