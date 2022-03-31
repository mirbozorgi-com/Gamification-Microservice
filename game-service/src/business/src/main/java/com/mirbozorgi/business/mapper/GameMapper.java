package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.GameInfo;
import com.mirbozorgi.core.entity.Game;
import java.util.stream.Collectors;

public class GameMapper {

  public static GameInfo toGameInfo(Game game) {
    return new GameInfo(
        game.getId(),
        game.getName(),
        game.getPackageName(),
        game.isActive(),
        game.getEnv(),
        game.getLastVersion(),
        game.getSupportedVersion(),
        game.getInstagram(),
        game.getTelegram(),
        game.getFacebook(),
        game.getTwitter(),
        game.getMarkets().stream().map(e -> e.getId()).collect(Collectors.toList()),
        game.getMarkets().stream().map(e -> e.getName()).collect(Collectors.toList()),
        game.getDefaultMarket().getId(),
        game.getDefaultMarket().getName(),
        game.getLinkedin(),
        game.getDefaultGem(),
        game.getDefaultGold(),
        game.getDefaultLevel(),
        game.getDefaultXp()
    );
  }
}
