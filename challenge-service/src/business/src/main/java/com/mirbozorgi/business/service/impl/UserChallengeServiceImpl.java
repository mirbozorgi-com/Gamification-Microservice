package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.domain.ChallengeData;
import com.mirbozorgi.business.domain.ClaimData;
import com.mirbozorgi.business.domain.IncScoreResponse;
import com.mirbozorgi.business.domain.UserChallengeData;
import com.mirbozorgi.business.exception.ChallengeActiveException;
import com.mirbozorgi.business.exception.ChallengeGameException;
import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.exception.UserBannedException;
import com.mirbozorgi.business.exception.UserChallengeClaimedException;
import com.mirbozorgi.business.exception.UserChallengeUpdateScoreException;
import com.mirbozorgi.business.exception.UserChallengeUpdateSoonerException;
import com.mirbozorgi.business.exception.UserClientVersionException;
import com.mirbozorgi.business.mapper.UserChallengeMapper;
import com.mirbozorgi.business.service.ChallengeService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.business.service.UserChallengeService;
import com.mirbozorgi.core.document.UserChallenge;
import com.mirbozorgi.core.repository.document.UserChallengeRepository;
import java.util.ArrayList;
import java.util.List;
import mirbozorgi.base.domain.leaderboard.LeaderBoardAddModel;
import mirbozorgi.base.domain.user.UserGetGlobalResponse;
import mirbozorgi.base.feignService.LeaderBoardFeignService;
import mirbozorgi.base.feignService.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserChallengeServiceImpl implements UserChallengeService {

  @Autowired
  private UserChallengeRepository userChallengeRepository;

  @Autowired
  private ChallengeService challengeService;

  @Autowired
  private TimeService timeService;

  @Autowired
  private UserFeignService userFeignService;

  @Autowired
  private LeaderBoardFeignService leaderBoardFeignService;

  @Value("${market.default-name}")
  private String defaultMarket;

  @Override
  public UserChallengeData addOrUpdate(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName) {

    ChallengeData challengeData = challengeService.findById(challengeId);

    if (!challengeData.isActive()) {
      throw new ChallengeActiveException();
    }

    if (!challengeData.getGamePackageName().equals(gamePackageName) ||
        !challengeData.getEnv().equals(env) ||
        !challengeData.getMarketName().equals(marketName)) {
      throw new ChallengeGameException();
    }

    UserGetGlobalResponse userGetResponse = userFeignService.getByUuId(
        userUuId,
        gamePackageName,
        env,
        marketName
    );

    if (userGetResponse == null) {
      throw new NotFoundException();
    }

    if (!(userGetResponse.getClientVersion() <= challengeData.getMaxClientVersion() &&
        userGetResponse.getClientVersion() >= challengeData.getMinClientVersion())) {
      throw new UserClientVersionException();
    }

    UserChallenge userChallenge = userChallengeRepository.addOrUpdate(
        new UserChallenge(
            challengeId,
            challengeData.getName(),
            gamePackageName,
            env,
            findMarketName(challengeId, marketName),
            userUuId,
            0,
            timeService.getNowUnixFromInstantClass(),
            0,
            false,
            false,
            challengeData.getEndTime(),
            userGetResponse.getUsername()
        ));

    //update leaderboard
    leaderBoardFeignService.add(new LeaderBoardAddModel(
            challengeId,
            userGetResponse.getUsername(),
            challengeData.getAllMarketInclude()),
        gamePackageName,
        userUuId,
        marketName,
        env);

    return UserChallengeMapper.toUserChallengeData(userChallenge);
  }

  @Override
  public UserChallengeData get(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName) {

    marketName = findMarketName(challengeId, marketName);

    UserChallenge userChallenge = userChallengeRepository.get(
        challengeId,
        userUuId,
        gamePackageName,
        env,
        marketName
    );
    if (userChallenge == null) {
      throw new NotFoundException();
    }

    return UserChallengeMapper.toUserChallengeData(userChallenge);
  }

  @Override
  public List<UserChallengeData> getAll(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName,
      Boolean claim,
      Boolean active) {
    List<UserChallengeData> userChallengeDataList = new ArrayList<>();
    marketName = findMarketName(challengeId, marketName);
    for (UserChallenge userChallenge : userChallengeRepository.getAll(
        challengeId,
        userUuId,
        gamePackageName,
        env,
        marketName,
        claim,
        active,
        timeService.getNowUnixFromInstantClass())) {
      userChallengeDataList.add(UserChallengeMapper.toUserChallengeData(userChallenge));
    }

    return userChallengeDataList;
  }

  @Override
  public IncScoreResponse incScore(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName,
      int score) {
    marketName = findMarketName(challengeId, marketName);
    long nowTime = timeService.getNowUnixFromInstantClass();
    UserChallengeData userChallengeData = get(
        challengeId,
        userUuId,
        gamePackageName,
        env,
        marketName);

    if (!userChallengeData.getActive()) {
      throw new ChallengeActiveException();
    }

    if (userChallengeData.getBanned()) {
      throw new UserBannedException();
    }

    if (userChallengeData.getClaim()) {
      throw new UserChallengeClaimedException();
    }
    ChallengeData challengeData = challengeService.findById(challengeId);

    // user banning action
    if (userChallengeData.getCheatUpdateRequest() >=
        challengeData.getLimitForUpdateRequestPerPeriod()) {
      updateBanned(
          challengeId,
          userUuId,
          gamePackageName,
          env,
          marketName,
          true
      );

      //update leaderboard for banned people
      leaderBoardFeignService.incScore(
          gamePackageName,
          marketName,
          env,
          challengeId,
          userUuId,
          Integer.MIN_VALUE
      );

      throw new UserBannedException();
    }

    //sooner update
    if (nowTime - userChallengeData.getLastUpdateScoreTime() <
        challengeData.getSecondBetweenTwoUpdatingScore()) {
      userChallengeRepository.incCheatInt(
          challengeId,
          userUuId,
          gamePackageName,
          env,
          marketName
      );
      throw new UserChallengeUpdateSoonerException();
    }

    // score update credential error
    if (score < challengeData.getMinScorePerUpdate() || score > challengeData
        .getMaxScorePerUpdate()) {
      userChallengeRepository.incCheatInt(
          challengeId,
          userUuId,
          gamePackageName,
          env,
          marketName
      );
      throw new UserChallengeUpdateScoreException();
    }

    int finalScore = userChallengeRepository.incScore(
        challengeId,
        userUuId,
        gamePackageName,
        env,
        marketName,
        score,
        nowTime
    );

    //update leaderboard
    leaderBoardFeignService.incScore(
        gamePackageName,
        marketName,
        env,
        challengeId,
        userUuId,
        score
    );

    return new IncScoreResponse(
        finalScore,
        challengeId,
        userUuId,
        gamePackageName,
        env,
        marketName
    );

  }

  @Override
  public void updateBanned(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName,
      boolean banned) {
    marketName = findMarketName(challengeId, marketName);
    userChallengeRepository.updateBanned(
        challengeId,
        userUuId,
        gamePackageName,
        env,
        marketName,
        banned
    );

  }

  @Override
  public ClaimData updateClaimed(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName) {

    boolean claim = true;
    ChallengeData challenge = challengeService.findById(challengeId);
    if (challenge.getAllMarketInclude()) {
      marketName = defaultMarket;
    }

    UserChallenge userChallenge = userChallengeRepository
        .get(challengeId, userUuId, gamePackageName, env, marketName);

    if (userChallenge.getClaim()) {
      throw new UserChallengeClaimedException();
    }

    userChallengeRepository.updateClaimed(
        challengeId,
        userUuId,
        gamePackageName,
        env,
        marketName,
        claim
    );

    return new ClaimData(
        challenge.getReward(),
        claim,
        challengeId,
        userUuId
    );
  }

  //////////////////////////////////////////////////
  /////////////////private methods///////////////////
  //////////////////////////////////////////////////
  private String findMarketName(String challengeId, String marketName) {
    ChallengeData challenge = challengeService.findById(challengeId);
    if (challenge.getAllMarketInclude()) {
      marketName = defaultMarket;
    }
    return marketName;
  }

}
