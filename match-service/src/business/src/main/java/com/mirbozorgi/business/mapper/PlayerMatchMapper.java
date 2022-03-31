package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.PlayerMatchData;
import com.mirbozorgi.core.entity.PlayerMatch;
import mirbozorgi.base.domain.user.WalletChange;

public class PlayerMatchMapper {

  public static PlayerMatchData playerMatchData(PlayerMatch playerMatch,
      Object matchConfig,
      WalletChange walletChange,
      int currentLevel) {

    return new PlayerMatchData(
        playerMatch.getStringId(),
        playerMatch.getMatchId(),
        playerMatch.getPlayers(),
        playerMatch.getPlayerMaxQuantity(),
        playerMatch.getHasEmptyPlaceForPlayer(),
        playerMatch.getPlayerUuIds(),
        playerMatch.getActive(),
        matchConfig,
        walletChange,
        currentLevel
    );

  }

  public static PlayerMatchData playerMatchData(PlayerMatch playerMatch,
      Object matchConfig,
      WalletChange walletChange) {

    return new PlayerMatchData(
        playerMatch.getStringId(),
        playerMatch.getMatchId(),
        playerMatch.getPlayers(),
        playerMatch.getPlayerMaxQuantity(),
        playerMatch.getHasEmptyPlaceForPlayer(),
        playerMatch.getPlayerUuIds(),
        playerMatch.getActive(),
        matchConfig,
        walletChange
    );

  }


}
