package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.MatchData;
import com.mirbozorgi.core.entity.Match;

public class MatchMapper {

  public static MatchData toMatchData(Match match) {

    return new MatchData(
        match.getStringId(),
        match.getGamePackageName(),
        match.getEnv(),
        match.getMarketName(),
        match.getName(),
        match.getStartTime(),
        match.getEndTime(),
        match.getMaxScorePerUpdate(),
        match.getMinScorePerUpdate(),
        match.getMinSecondBetweenTwoUpdatingScore(),
        match.getMaxSecondBetweenTwoUpdatingScore(),
        match.getLimitErrorForUpdateRequestPerPeriod(),
        match.getMaxPlayerInMatch(),
        match.getMinPlayerInMatch(),
        match.getMinClientVersion(),
        match.getMaxClientVersion(),
        match.getReward(),
        match.getConfig(),
        match.getMaxXpForJoinOffline(),
        match.getMinXpForJoinOffline(),
        match.getGemCost(),
        match.getGoldCost(),
        match.getGemWin(),
        match.getGoldWin(),
        match.getWinXpLooser(),
        match.getWinXpWinner(),
        match.getWinnerXpVipCoefficient(),
        match.getLooserXpVipCoefficient(),
        match.getGemBonus(),
        match.getGoldBonus(),
        match.getXpBonus()

    );


  }


}
