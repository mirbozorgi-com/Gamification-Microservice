package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.entity.PlayerGemBuyHistoryTime;
import java.util.List;

public interface PlayerGemBuyHistoryTimeRepository {

  PlayerGemBuyHistoryTime add(PlayerGemBuyHistoryTime playerGemBuyHistoryTime);

  List<PlayerGemBuyHistoryTime> getLeaderBoard(
      int topNumber,
      String gamePackageName,
      String env,
      String marketName);

  PlayerGemBuyHistoryTime get(
      String uuid,
      String gamePackageName,
      String env,
      String marketName);


}





