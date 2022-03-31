package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.ItemShopData;
import com.mirbozorgi.core.docuemnt.ItemShop;

public class ItemShopMapper {


  public static ItemShopData toItemShopData(ItemShop itemShop, String name) {
    return new ItemShopData(
        itemShop.getStringId(),
        name,
        itemShop.getGamePackageName(),
        itemShop.getEnv(),
        itemShop.getMarket(),
        itemShop.getItems().get(name).getConfig(),
        itemShop.getItems().get(name).getType(),
        itemShop.getItems().get(name).getFileUrl(),
        itemShop.getItems().get(name).getVipPeriodTime(),
        itemShop.getItems().get(name).getXp(),
        itemShop.getItems().get(name).getGold(),
        itemShop.getItems().get(name).getGem(),
        itemShop.getItems().get(name).getLevel(),
        itemShop.getItems().get(name).getAvatarPurchaseIds());
  }

}
