package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.entity.PlayerGemBuy;
import com.mirbozorgi.core.entity.PlayerRefral;
import com.mirbozorgi.core.entity.PlayerXp;
import com.mirbozorgi.core.entity.PlayerXpWithTime;
import com.mirbozorgi.core.entity.PlayerScore;
import java.util.List;

public interface LeaderBoardRepository {

  List<PlayerScore> byScore(
      int topNumber,
      String gamePackageName,
      String marketName,
      String env,
      String challengeId);

  List<PlayerXp> byXp(
      int topNumber,
      String gamePackageName,
      String marketName,
      String env);

  List<PlayerXpWithTime> byXpLastSevenDays(
      int topNumber,
      String gamePackageName,
      String marketName,
      String env);


  List<PlayerRefral> byMostRefralLastSevenDays(
      int topNumber,
      String gamePackageName,
      String marketName,
      String env);

  List<PlayerGemBuy> theMostBuyerGem(
      int topNumber,
      String gamePackageName,
      String marketName,
      String env);


}
