package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.PlayerRankScoreData;
import com.mirbozorgi.business.domain.PlayerGemBuyRankData;
import com.mirbozorgi.business.domain.PlayerRankGemBuyHistoryData;
import com.mirbozorgi.business.domain.PlayerRefralRankData;
import com.mirbozorgi.business.domain.PlayerXpRankData;
import com.mirbozorgi.core.domain.PlayerGameScore;
import com.mirbozorgi.core.domain.PlayerGemBuyData;
import com.mirbozorgi.core.domain.PlayerRefralData;
import com.mirbozorgi.core.domain.PlayerXpData;
import com.mirbozorgi.core.entity.PlayerGemBuy;
import com.mirbozorgi.core.entity.PlayerGemBuyHistoryTime;
import com.mirbozorgi.core.entity.PlayerRefral;
import com.mirbozorgi.core.entity.PlayerScore;
import com.mirbozorgi.core.entity.PlayerXp;
import com.mirbozorgi.core.entity.PlayerXpWithTime;
import java.util.ArrayList;
import java.util.List;

public class PlayerRankMapper {

  public static List<PlayerRankScoreData> toPlayerRankData(
      List<PlayerScore> playerScores,
      String gamePackageName,
      String challengeId,
      String env,
      String marketName) {
    List<PlayerRankScoreData> playerRankScoreData = new ArrayList<>();
    gamePackageName = gamePackageName.replace(".", "_");
    int rank = 0;
    for (PlayerScore playerScore : playerScores) {
      PlayerGameScore playerGameScore = playerScore.getPlayerarsalancore().get(gamePackageName)
          .get(env).get(marketName).get(challengeId);

      rank = rank + 1;
      playerRankScoreData.add(new PlayerRankScoreData(
          playerScore.getUserUuId(),
          playerGameScore.getUsername(),
          rank,
          playerGameScore.getScore()
      ));

    }
    return playerRankScoreData;

  }

  public static List<PlayerXpRankData> toPlayerRankXp(
      List<PlayerXp> playerScores,
      String gamePackageName,
      String env,
      String marketName) {
    List<PlayerXpRankData> playerRankScoreData = new ArrayList<>();
    gamePackageName = gamePackageName.replace(".", "_");
    int rank = 0;
    for (PlayerXp playerScore : playerScores) {
      PlayerXpData playerarsalancore = playerScore.getPlayerXpDatas().get(gamePackageName)
          .get(env).get(marketName);

      rank = rank + 1;
      playerRankScoreData.add(new PlayerXpRankData(
          playerScore.getUserUuId(),
          playerarsalancore.getUsername(),
          rank,
          playerarsalancore.getXp(),
          playerarsalancore.getAvatarActiveIds(),
          playerarsalancore.getEndVipTime(),
          playerarsalancore.getLevel()
      ));

    }
    return playerRankScoreData;

  }

  public static List<PlayerXpRankData> toPlayerXpTimeRankData(
      List<PlayerXpWithTime> playerScores,
      String gamePackageName,
      String env,
      String marketName) {
    List<PlayerXpRankData> playerRankScoreData = new ArrayList<>();
    gamePackageName = gamePackageName.replace(".", "_");
    int rank = 0;
    for (PlayerXpWithTime playerScore : playerScores) {
      PlayerXpData playerarsalancore = playerScore.getPlayerXpDatas().get(gamePackageName)
          .get(env).get(marketName);

      rank = rank + 1;
      playerRankScoreData.add(new PlayerXpRankData(
          playerScore.getUserUuId(),
          playerarsalancore.getUsername(),
          rank,
          playerarsalancore.getXp(),
          playerarsalancore.getAvatarActiveIds(),
          playerarsalancore.getEndVipTime(),
          playerarsalancore.getLevel()
      ));

    }
    return playerRankScoreData;

  }

  public static List<PlayerGemBuyRankData> toPlayerGemRank(
      List<PlayerGemBuy> playerScores,
      String gamePackageName,
      String env,
      String marketName) {
    List<PlayerGemBuyRankData> playerRankScoreData = new ArrayList<>();
    gamePackageName = gamePackageName.replace(".", "_");
    int rank = 0;
    for (PlayerGemBuy playerScore : playerScores) {
      PlayerGemBuyData playerarsalancore = playerScore.getPlayerGaemBuy().get(gamePackageName)
          .get(env).get(marketName);

      rank = rank + 1;
      playerRankScoreData.add(new PlayerGemBuyRankData(
          playerScore.getUserUuId(),
          playerarsalancore.getUsername(),
          rank,
          playerarsalancore.getLevel(),
          playerarsalancore.getGem(),
          playerarsalancore.getAvatarActiveIds(),
          playerarsalancore.getEndVipTime()
      ));

    }
    return playerRankScoreData;

  }


  public static List<PlayerRankGemBuyHistoryData> toGemByHistoryRank(
      List<PlayerGemBuyHistoryTime> playerGemBuyHistoryTimes,
      String gamePackageName,
      String env,
      String marketName) {
    List<PlayerRankGemBuyHistoryData> playerRankScoreData = new ArrayList<>();
    gamePackageName = gamePackageName.replace(".", "_");
    for (PlayerGemBuyHistoryTime playerGemBuyHistoryTime : playerGemBuyHistoryTimes) {
      PlayerGemBuyData playerGemBuyData = playerGemBuyHistoryTime.getPlayerMostBuyGem()
          .get(gamePackageName)
          .get(env).get(marketName);

      playerRankScoreData.add(new PlayerRankGemBuyHistoryData(
          playerGemBuyHistoryTime.getUserUuId(),
          playerGemBuyData.getUsername(),
          playerGemBuyData.getGem(),
          playerGemBuyData.getLevel(),
          playerGemBuyData.getDate(),
          playerGemBuyData.getAvatarActiveIds(),
          playerGemBuyData.getEndVipTime()
      ));

    }
    return playerRankScoreData;

  }


  public static List<PlayerRefralRankData> toPlayerRefralRank(
      List<PlayerRefral> playerScores,
      String gamePackageName,
      String env,
      String marketName) {
    List<PlayerRefralRankData> playerRankScoreData = new ArrayList<>();
    gamePackageName = gamePackageName.replace(".", "_");
    int rank = 0;
    for (PlayerRefral playerScore : playerScores) {
      PlayerRefralData playerarsalancore = playerScore.getPlayerRefrals().get(gamePackageName)
          .get(env).get(marketName);

      rank = rank + 1;
      playerRankScoreData.add(new PlayerRefralRankData(
          playerScore.getUserUuId(),
          playerarsalancore.getUsername(),
          rank,
          playerarsalancore.getQuantity(),
          playerarsalancore.getLevel(),
          playerarsalancore.getAvatarActiveIds(),
          playerarsalancore.getEndVipTime()
      ));

    }
    return playerRankScoreData;

  }


}
