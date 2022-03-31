package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.GameMemoryService;
import com.mirbozorgi.core.entity.Game;
import com.mirbozorgi.core.repository.entity.GameRepository;
import com.mirbozorgi.core.repository.memory.DefaultMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameMemoryServiceImpl implements GameMemoryService {

  @Autowired
  private DefaultMarketRepository defaultMarketRepository;

  @Autowired
  private GameRepository gameRepository;

  @Override
  public String getDefaultMarket(
      String gamePackageName,
      String env) {
    String defaultMarket = defaultMarketRepository.get(gamePackageName, env);
    if (defaultMarket == null) {
      Game game = gameRepository.findByPackageNameAndEnv(gamePackageName, env);
      defaultMarket = game.getDefaultMarket().getName();
      defaultMarketRepository.add(gamePackageName, env, defaultMarket);
    }
    return defaultMarket;
  }
}
