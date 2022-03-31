package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.PlayerHistoryGemDailyInfo;
import com.mirbozorgi.business.domain.PlayerRankScoreData;
import com.mirbozorgi.business.domain.PlayerRankingWithTimeData;
import com.mirbozorgi.core.entity.PlayerGemBuyHistoryTime;
import com.mirbozorgi.core.entity.PlayerXpWithTime;
import java.util.List;

public interface LeaderBoardService {


  Object reset(
      String gamePackageName,
      String marketName,
      String env,
      String apiKey);


  List<PlayerRankScoreData> byScore(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env,
      String challengeId);

  PlayerRankingWithTimeData byXp(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env);

  PlayerRankingWithTimeData byXpLastSevenDays(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env,
      String uuid);

  List<PlayerXpWithTime> ByXpLastSevenDaysWithCache(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env);

  PlayerRankingWithTimeData byMostRefralLastSevenDays(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env,
      String uuid);

  PlayerRankingWithTimeData theMostBuyerGem(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env);

  //for add every week first person and delete others
  PlayerHistoryGemDailyInfo theMostWeeklyHistoryGem(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env,
      String uuid);

  List<PlayerGemBuyHistoryTime> theMostWeeklyHistoryGemWithCache(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env);
}
