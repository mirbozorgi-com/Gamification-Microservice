package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.exception.MarketException;
import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.service.ChallengeService;
import com.mirbozorgi.business.domain.ChallengeData;
import com.mirbozorgi.business.mapper.ChallengeMapper;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.core.constant.ChallengeType;
import com.mirbozorgi.core.document.Challenge;
import com.mirbozorgi.core.repository.document.ChallengeRepository;
import java.util.ArrayList;
import java.util.List;
import mirbozorgi.base.domain.game.GameInfo;
import mirbozorgi.base.feignService.GameFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeServiceImpl implements ChallengeService {

  @Autowired
  private ChallengeRepository challengeRepository;

  @Autowired
  private GameFeignService gameFeignService;


  @Autowired
  private TimeService timeService;


  @Override
  public ChallengeData save(
      String name,
      long startTime,
      long endTime,
      long maxScorePerUpdate,
      long minScorePerUpdate,
      long secondBetweenTwoUpdatingScore,
      long limitForUpdateRequestPerPeriod,
      Object reward,
      ChallengeType type,
      String gamePackageName,
      String env,
      String marketName,
      Boolean allMarketInclude,
      int minClientVersion,
      int maxClientVersion) {

    GameInfo gameInfo = gameFeignService.getByPackageNameAndEnv(gamePackageName, env);

    if (!gameInfo.getMarketNames().contains(marketName)) {
      throw new MarketException();
    }

    Challenge challenge = challengeRepository.save(
        new Challenge(
            name,
            startTime,
            endTime,
            maxScorePerUpdate,
            minScorePerUpdate,
            secondBetweenTwoUpdatingScore,
            limitForUpdateRequestPerPeriod,
            reward,
            type,
            gamePackageName,
            env,
            marketName,
            allMarketInclude,
            minClientVersion,
            maxClientVersion
        )
    );

    return ChallengeMapper.toChallengeData(
        challenge
    );

  }

  @Override
  public ChallengeData findById(String id) {
    Challenge challenge = challengeRepository.findById(id);

    if (challenge == null) {
      throw new NotFoundException();
    }

    return ChallengeMapper.toChallengeData(challenge);
  }

  @Override
  public ChallengeData update(
      String id,
      String name,
      long startTime,
      long endTime,
      long maxScorePerUpdate,
      long minScorePerUpdate,
      long secondBetweenTwoUpdatingScore,
      long limitForUpdateRequestPerPeriod,
      Object reward,
      ChallengeType type,
      String gamePackageName,
      String env,
      String marketName,
      Boolean allMarketInclude,
      int minClientVersion,
      int maxClientVersion) {

    GameInfo gameInfo = gameFeignService.getByPackageNameAndEnv(gamePackageName, env);

    if (!gameInfo.getMarketNames().contains(marketName)) {
      throw new MarketException();
    }

    return ChallengeMapper.toChallengeData(challengeRepository.update(
        id,
        name,
        startTime,
        endTime,
        maxScorePerUpdate,
        minScorePerUpdate,
        secondBetweenTwoUpdatingScore,
        limitForUpdateRequestPerPeriod,
        reward,
        type,
        gamePackageName,
        env,
        marketName,
        allMarketInclude,
        minClientVersion,
        maxClientVersion
    ));
  }

  @Override
  public ChallengeData findBy(String gamePackageName, String env, String marketName, String name) {
    return ChallengeMapper.toChallengeData(challengeRepository.findBy(
        gamePackageName,
        env,
        marketName,
        name
    ));
  }

  @Override
  public void delete(String id) {
    challengeRepository.delete(challengeRepository.findById(id));

  }

  @Override
  public List<ChallengeData> findAll(String gamePackageName, String env, String marketName,
      String name, Boolean active) {
    List<ChallengeData> challengeDatas = new ArrayList<>();

    List<Challenge> challenges = challengeRepository.findAll(
        gamePackageName,
        env,
        marketName,
        name,
        active,
        timeService.getNowUnixFromInstantClass()

    );
    for (Challenge challenge : challenges) {
      challengeDatas.add(ChallengeMapper.toChallengeData(challenge));
    }

    return challengeDatas;
  }

  @Override
  public boolean isIncludeAllMarket(String id) {
    return challengeRepository.isIncludeAllMarket(id);
  }
}
