package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.SkuInfo;
import com.mirbozorgi.core.entity.SKU;

public class SkuMapper {

  public static SkuInfo toSkuData(SKU sku) {
    return new SkuInfo(
        sku.getId(),
        sku.getGame().getId(),
        sku.getName(),
        sku.getDescription(),
        sku.getAmount(),
        sku.getCurrencyType().toString(),
        sku.isActive(),
        sku.isConsumable()
    );
  }

}
