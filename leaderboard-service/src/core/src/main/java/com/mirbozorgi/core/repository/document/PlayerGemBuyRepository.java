package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.domain.PlayerGemBuyData;
import com.mirbozorgi.core.entity.PlayerGemBuy;

public interface PlayerGemBuyRepository {

  void upsert(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      PlayerGemBuyData playerGemBuyData);

  PlayerGemBuy find(
      String uuid,
      String gamepackageName,
      String env,
      String marketName
  );


  void reset(
      String gamePackageName,
      String env,
      String marketName
  );
}