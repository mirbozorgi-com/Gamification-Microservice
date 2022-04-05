package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.business.domain.LeaderBoardRewardInfo;
import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.mapper.LeaderBoardRewardMapper;
import com.mirbozorgi.business.service.LeaderBoardRewardService;
import com.mirbozorgi.core.domain.LeaderBoardType;
import com.mirbozorgi.core.entity.LeaderBoardReward;
import com.mirbozorgi.core.entity.PlayerGemBuy;
import com.mirbozorgi.core.entity.PlayerRefral;
import com.mirbozorgi.core.entity.PlayerXp;
import com.mirbozorgi.core.entity.PlayerXpWithTime;
import com.mirbozorgi.core.repository.document.LeaderBoardRepository;
import com.mirbozorgi.core.repository.document.LeaderBoardRewardRepository;
import java.util.ArrayList;
import java.util.List;
import mirbozorgi.base.domain.game.WalletChangeModel;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;
import mirbozorgi.base.domain.user.WalletChange;
import mirbozorgi.base.feignService.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class LeaderBoardRewardServiceImpl implements LeaderBoardRewardService {

  @Autowired
  private LeaderBoardRewardRepository leaderBoardRewardRepository;

  @Autowired
  private LeaderBoardRepository leaderBoardRepository;

  @Autowired
  private UserFeignService userFeignService;

  @Autowired
  private UserHamiFeginService userHamiFeginService;

  @Autowired
  private TimeService timeService;

  @Value("${api.key.walletchange}")
  String apiKeyWalletChange;

  @Override
  public LeaderBoardRewardInfo add(
      String gamePackageName,
      String env,
      String marketName,
      LeaderBoardType type,
      int gem,
      int gold,
      String level,
      int xp,
      boolean hamiAdded,
      List<Integer> avatarIds,
      long addedVipPeriodTime) {
    LeaderBoardReward leaderBoardReward = leaderBoardRewardRepository.add(
        gamePackageName,
        env,
        marketName,
        type,
        new WalletChange(gem, gold, Short.valueOf(level), xp, hamiAdded, avatarIds,
            addedVipPeriodTime));

    return LeaderBoardRewardMapper.toInfo(leaderBoardReward);
  }

  @Override
  public LeaderBoardRewardInfo update(
      String gamePackageName,
      String env,
      String marketName,
      LeaderBoardType type,
      List<WalletChange> walletChanges) {
    LeaderBoardReward update = leaderBoardRewardRepository.update(
        gamePackageName,
        env,
        marketName,
        type,
        walletChanges
    );

    return LeaderBoardRewardMapper.toInfo(update);
  }

  @Override
  public void delete(String id) {
    leaderBoardRewardRepository.delete(id);
  }

  @Cacheable(value = "base", key = "{ #root.targetClass.simpleName, "
      + "#root.methodName, #gamePackageName, #env,#marketName}", sync = true)
  @Override
  public LeaderBoardRewardInfo findBy(
      String gamePackageName,
      String env,
      String marketName) {
    LeaderBoardReward leaderBoardReward = leaderBoardRewardRepository
        .findBy(gamePackageName, env, marketName);
    if (leaderBoardReward == null) {
      throw new NotFoundException();
    }
    return LeaderBoardRewardMapper.toInfo(leaderBoardReward);
  }

  @Override
  public List<WalletChange> findRewardByType(
      LeaderBoardType type,
      String gamePackageName,
      String env,
      String marketName) {

    List<WalletChange> walletChanges = leaderBoardRewardRepository.findBy(
        gamePackageName,
        env,
        marketName
    ).getRewards()
        .get(type);

    if (walletChanges == null) {
      return new ArrayList<>();
    }
    return walletChanges;


  }

  @Override
  public WalletChange claim(
      LeaderBoardType type,
      String gamePackageName,
      String env,
      String marketName,
      String uuid,
      String username,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endVipTime
  ) {
    WalletChange finalWalletChange = null;
    List<WalletChange> rewardByType = findRewardByType(type,
        gamePackageName,
        env,
        marketName);
    if (type.equals(LeaderBoardType.XP_TIME)) {
      List<PlayerXpWithTime> playerXpWithTimes = leaderBoardRepository
          .byXpLastSevenDays(rewardByType.size(), gamePackageName, marketName, env);

      for (int i = 0; i < playerXpWithTimes.size(); i++) {
        if (playerXpWithTimes.get(i).getUserUuId().equals(uuid)) {
          finalWalletChange = calculate(rewardByType.get(i),
              gamePackageName,
              env,
              marketName,
              uuid,
              username,
              currentLevel,
              currentAvatarIds,
              endVipTime);
          break;
        }
      }


    } else if (type.equals(LeaderBoardType.XP)) {

      List<PlayerXp> playerXps = leaderBoardRepository
          .byXp(rewardByType.size(), gamePackageName, marketName, env);

      for (int i = 0; i < playerXps.size(); i++) {
        if (playerXps.get(i).getUserUuId().equals(uuid)) {
          finalWalletChange = calculate(rewardByType.get(i),
              gamePackageName,
              env,
              marketName,
              uuid,
              username,
              currentLevel,
              currentAvatarIds,
              endVipTime);
          break;
        }
      }


    } else if (type.equals(LeaderBoardType.GEM_BUY)) {
      List<PlayerGemBuy> playerGemBuys = leaderBoardRepository
          .theMostBuyerGem(rewardByType.size(), gamePackageName, marketName, env);
      for (int i = 0; i < playerGemBuys.size(); i++) {
        if (playerGemBuys.get(i).getUserUuId().equals(uuid)) {
          finalWalletChange = calculate(
              rewardByType.get(i),
              gamePackageName,
              env,
              marketName,
              uuid,
              username,
              currentLevel,
              currentAvatarIds,
              endVipTime);
          break;
        }
      }

    } else if (type.equals(LeaderBoardType.REFRAL)) {
      List<PlayerRefral> playerRefrals = leaderBoardRepository
          .byMostRefralLastSevenDays(rewardByType.size(), gamePackageName, marketName, env);

      for (int i = 0; i < playerRefrals.size(); i++) {
        if (playerRefrals.get(i).getUserUuId().equals(uuid)) {
          finalWalletChange = calculate(
              rewardByType.get(i),
              gamePackageName,
              env,
              marketName,
              uuid,
              username,
              currentLevel,
              currentAvatarIds,
              endVipTime);
          break;
        }
      }

    }

    //TODO:n=badan k challenge ezafe shod ezafe shawad
//    if (type.equals(LeaderBoardType.SCORE)) {
//    }

    return finalWalletChange;
  }

  ///////////////////////////////////////////////
  /////////////////private methods////////////////
  ///////////////////////////////////////////////

  private WalletChange calculate(
      WalletChange walletChange,
      String gamePackageName,
      String env,
      String marketName,
      String uuid,
      String username,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endVipTime) {

    /////wallet changing ////////////////

    userFeignService.walletChange(
        apiKeyWalletChange,
        new WalletChangeModel(
            walletChange.getGem(),
            walletChange.getGold(),
            walletChange.getLevel(),
            walletChange.getXp(),
            currentAvatarIds,
            currentLevel,
            false,
            endVipTime
        ),
        gamePackageName,
        uuid,
        env,
        marketName
    );

    if (walletChange.getAvatarIds().size() != 0) {

      userFeignService.addAvatarPurchase(
          uuid,
          gamePackageName,
          env,
          marketName,
          walletChange.getAvatarIds()
      );


    }
    if (walletChange.getAddedVipPeriodTime() != 0) {
      userHamiFeginService.addVipUser(
          uuid,
          timeService.getNowUnixFromInstantClass(),
          walletChange.getAddedVipPeriodTime() + timeService.getNowUnixFromInstantClass(),
          walletChange.getAddedVipPeriodTime(),
          gamePackageName,
          env,
          marketName
      );
    }

    if (walletChange.isHamiAded()) {
      userHamiFeginService.addHami(
          uuid,
          username,
          "BY_LEADER_BOARD",
          null,
          HamiAndNotificationType.GIFT_CODE,
          gamePackageName,
          env,
          marketName);
    }
    /////wallet changing ////////////////

    return walletChange;
  }
}
