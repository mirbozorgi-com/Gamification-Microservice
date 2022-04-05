package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.domain.DailyRewardContinuesInfo;
import com.mirbozorgi.business.domain.GameInfo;
import com.mirbozorgi.business.mapper.DailyRewardContinuesMapper;
import com.mirbozorgi.business.service.DailyRewardContinuesService;
import com.mirbozorgi.business.service.GameService;
import com.mirbozorgi.core.document.DailyRewardContinues;
import com.mirbozorgi.core.domain.WalletChangeModelDailyReward;
import com.mirbozorgi.core.repository.docuemnt.DailyRewardContinuesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyRewardContinuesServiceImpl implements DailyRewardContinuesService {


  @Autowired
  private DailyRewardContinuesRepository repository;

  @Autowired
  private GameService gameService;

  @Override
  public DailyRewardContinuesInfo update(
      List<WalletChangeModelDailyReward> walletChangeModels,
      String name,
      int gameId) {
    GameInfo game = gameService.getById(gameId);
    DailyRewardContinues update = repository.update(
        walletChangeModels,
        name,
        gameId,
        game.getPackageName(),
        game.getEnv()
    );

    return DailyRewardContinuesMapper.toInfo(update, name);
  }

  @Override
  public List<WalletChangeModelDailyReward> getAllFlow(
      String gamePackageName,
      String env,
      String name) {

    return repository.getAllDailyFlows(
        gamePackageName,
        env,
        name
    );
  }
}
