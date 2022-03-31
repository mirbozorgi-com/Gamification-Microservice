package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.WalletChangeModelDailyReward;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import mirbozorgi.base.domain.user.WalletChange;

public class WalletChangeModelMapper {

  public static List<WalletChangeModelDailyReward> toWalletChangeModel(ArrayList allDailyContinues,
      boolean gemBuy) {
    List<WalletChangeModelDailyReward> walletChangeModels = new ArrayList<>();

    for (Object allDailyContinue : allDailyContinues) {
      Object gem = ((LinkedHashMap) allDailyContinue).get("gem");
      Object gold = ((LinkedHashMap) allDailyContinue).get("gold");
      Object level = ((LinkedHashMap) allDailyContinue).get("level");
      Object xp = ((LinkedHashMap) allDailyContinue).get("xp");
      Object timeOfVip = ((LinkedHashMap) allDailyContinue).get("vipTimeAdded");
      Object hamiAdded = ((LinkedHashMap) allDailyContinue).get("hamiAdded");
      Object avatarIds = ((LinkedHashMap) allDailyContinue).get("avatarIds");
      List<Integer> avatarIdsInt = new ArrayList<>();
      Object[] objects = ((ArrayList) avatarIds).toArray();
      for (Object object : objects) {
        avatarIdsInt.add(Integer.parseInt(object.toString()));
      }

      short levelShort = 0;
      int gemInt = Integer.parseInt(gem.toString());
      int goldInt = Integer.parseInt(gold.toString());
      if (level != null) {
        levelShort = Short.parseShort(level.toString());
      }
      int xpInt = Integer.parseInt(xp.toString());

      walletChangeModels.add(
          new WalletChangeModelDailyReward(
              gemInt,
              goldInt,
              levelShort,
              xpInt,
              gemBuy,
              Boolean.parseBoolean(hamiAdded.toString()),
              Long.parseLong(timeOfVip.toString()),
              avatarIdsInt

          )
      );

    }

    return walletChangeModels;
  }

  public static WalletChange toWalletChange(Object o) {
    o = ((LinkedHashMap) o).get("walletChange");
    Object avatarIdObjects = ((LinkedHashMap) o).get("avatarIds");
    List<Integer> avatarIds = new ArrayList<>();

//    Object[] avatarIds1 = Collections(((LinkedHashMap) o).get("avatarIds")).toArray();
    for (int i = 0; i < ((ArrayList) avatarIdObjects).size(); i++) {
      int value = Integer.parseInt(((ArrayList) avatarIdObjects).get(i).toString());
      avatarIds.add(value);
    }

    return new WalletChange(
        Integer.parseInt(((LinkedHashMap) o).get("gem").toString()),
        Integer.parseInt(((LinkedHashMap) o).get("gold").toString()),
        Short.parseShort(((LinkedHashMap) o).get("level").toString()),
        Integer.parseInt(((LinkedHashMap) o).get("xp").toString()),
        Boolean.parseBoolean(((LinkedHashMap) o).get("hamiAded").toString()),
        avatarIds,
        Long.parseLong(((LinkedHashMap) o).get("addedVipPeriodTime").toString())
    );
  }

}
