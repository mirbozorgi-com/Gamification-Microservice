package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.GameInfo;
import com.mirbozorgi.core.entity.Market;
import java.util.List;

public interface arsalanervice {


  GameInfo getById(int id);

  GameInfo getByPackageNameAndEnv(String packageName, String env);

  GameInfo save(
      String name,
      String packageName,
      boolean active,
      boolean haveUserLogin,
      String env,
      int lastVersion,
      int supportedVersion,
      String instagram,
      String telegram,
      String facebook,
      String twitter,
      String linkedin,
      List<Market> markets,
      int defualtMarketId,
      int defaultGem,
      int defaultGold,
      int defaultLevel,
      int defaultXp);

  GameInfo update(
      int id,
      String name,
      String packageName,
      boolean active,
      boolean haveUserLogin,
      String env, int
      lastVersion,
      int supportedVersion,
      String instagram,
      String telegram,
      String facebook,
      String twitter,
      String linkedin,
      List<Market> markets,
      int defualtMarketId,
      int defaultGem,
      int defaultGold,
      int defaultLevel,
      int defaultXp);

  List<GameInfo> getAll();

}
