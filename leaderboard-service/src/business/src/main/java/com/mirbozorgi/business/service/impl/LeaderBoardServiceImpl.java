package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.domain.PlayerHistoryGemDailyInfo;
import com.mirbozorgi.business.domain.PlayerRankScoreData;
import com.mirbozorgi.business.domain.PlayerRankingWithTimeData;
import com.mirbozorgi.business.mapper.PlayerRankMapper;
import com.mirbozorgi.business.service.LeaderBoardRewardService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.business.domain.LeaderBoardRewardInfo;
import com.mirbozorgi.business.service.LeaderBoardService;
import com.mirbozorgi.core.domain.ConfigData;
import com.mirbozorgi.core.domain.LeaderBoardType;
import com.mirbozorgi.core.domain.PlayerGemBuyData;
import com.mirbozorgi.core.domain.PlayerRefralData;
import com.mirbozorgi.core.entity.Config;
import com.mirbozorgi.core.entity.PlayerGemBuy;
import com.mirbozorgi.core.entity.PlayerGemBuyHistoryTime;
import com.mirbozorgi.core.entity.PlayerRefral;
import com.mirbozorgi.core.entity.PlayerScore;
import com.mirbozorgi.core.entity.PlayerXp;
import com.mirbozorgi.core.entity.PlayerXpWithTime;
import com.mirbozorgi.core.repository.document.ConfigRepository;
import com.mirbozorgi.core.repository.document.LeaderBoardRepository;
import com.mirbozorgi.core.repository.document.PlayerGemBuyHistoryTimeRepository;
import com.mirbozorgi.core.repository.document.PlayerGemBuyRepository;
import com.mirbozorgi.core.repository.document.PlayerRefralRepository;
import com.mirbozorgi.core.repository.document.PlayerXpRepository;
import com.mirbozorgi.core.repository.memory.LeaderBoardMemoryRepository;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import mirbozorgi.base.constanct.EnumKeyFCM;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;
import mirbozorgi.base.domain.notification.AddNotificationToOneUserModel;
import mirbozorgi.base.domain.notification.FCMGameMessageInfo;
import mirbozorgi.base.domain.notification.FCMGameMessageInfoGetAll;
import mirbozorgi.base.domain.user.WalletChange;
import mirbozorgi.base.exception.UnAuthorizedException;
import mirbozorgi.base.feignService.ChallengeFeignService;
import mirbozorgi.base.feignService.NotificationFeignService;
import mirbozorgi.base.feignService.UserFeignService;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class LeaderBoardServiceImpl implements LeaderBoardService {

  @Autowired
  private LeaderBoardRepository leaderBoardRepository;

  @Value("${market.default-name}")
  String defaultMarket;

  @Value("${leaderboard.reset.api.key}")
  String leaderboardResetApiKey;

  @Autowired
  private ChallengeFeignService challengeFeignService;

  @Autowired
  private PlayerGemBuyHistoryTimeRepository playerGemBuyHistoryTimeRepository;

  @Autowired
  private ConfigRepository configRepository;

  @Autowired
  private PlayerXpRepository playerXpRepository;

  @Autowired
  private PlayerRefralRepository playerRefralRepository;

  @Autowired
  private PlayerGemBuyRepository playerGemBuyRepository;

  @Autowired
  private TimeService timeService;

  @Autowired
  private NotificationFeignService notificationFeignService;

  @Autowired
  private LeaderBoardRewardService leaderBoardRewardService;

  @Autowired
  private UserFeignService userFeignService;

  @Autowired
  private LeaderBoardMemoryRepository leaderBoardMemoryRepository;

  @Override
  public Object reset(
      String gamePackageName,
      String marketName,
      String env,
      String apiKey) {

    if (!apiKey.equals(leaderboardResetApiKey)) {
      throw new UnAuthorizedException();
    }
    Config config = configRepository.get(gamePackageName, env, marketName);
    calculationJobs(config);
    return "DONE!";
  }

  @Cacheable(value = "base", key = "{ #root.targetClass.simpleName, "
      + "#root.methodName, #topNumber, #gamePackageName, #env,#marketName,#challengeId}", sync = true)
  @Override
  public List<PlayerRankScoreData> byScore(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env,
      String challengeId) {

    if (topNumber == null) {
      topNumber = 100;
    }
    if (topNumber == 0) {
      topNumber = 100;
    }
    if (marketName == null) {
      marketName = "all-markets";
    }
    marketName = findMarketName(challengeId, marketName);

    List<PlayerScore> playerScores = leaderBoardRepository.byScore(
        topNumber,
        gamePackageName,
        marketName,
        env,
        challengeId
    );

    return PlayerRankMapper
        .toPlayerRankData(playerScores, gamePackageName, challengeId, env, marketName);
  }

  @Cacheable(value = "base", key = "{ #root.targetClass.simpleName, "
      + "#root.methodName, #topNumber, #gamePackageName, #env,#marketName}", sync = true)
  @Override
  public PlayerRankingWithTimeData byXp(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env) {

    if (topNumber == null) {
      topNumber = 100;
    }
    if (topNumber == 0) {
      topNumber = 100;
    }
    if (marketName == null) {
      marketName = "all-markets";
    }

    List<PlayerXp> playerXps = leaderBoardRepository.byXp(topNumber,
        gamePackageName,
        marketName,
        env);

    return new PlayerRankingWithTimeData(
        PlayerRankMapper
            .toPlayerRankXp(playerXps, gamePackageName, env, marketName),
        Long.MAX_VALUE,
        -1
    );

  }

  @Override
  public PlayerRankingWithTimeData byXpLastSevenDays(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env,
      String uuid) {
    int ownScore = -1;
    if (uuid != null) {
      PlayerXpWithTime playerXpWithTime = playerXpRepository.findXpTime(
          uuid,
          gamePackageName,
          env,
          marketName
      );
      if (playerXpWithTime != null) {
        if (playerXpWithTime.getPlayerXpDatas().size() == 0) {
          return new PlayerRankingWithTimeData(new ArrayList<>(), 0, 0);
        }
        ownScore = playerXpWithTime.getPlayerXpDatas()
            .get(fix(gamePackageName))
            .get(env)
            .get(marketName)
            .getXp();
      }
    }

    if (topNumber == null) {
      topNumber = 100;
    }
    if (topNumber == 0) {
      topNumber = 100;
    }
    if (marketName == null) {
      marketName = "all-markets";
    }
    //cache this
    List<PlayerXpWithTime> playerXpWithTimes = ByXpLastSevenDaysWithCache(topNumber,
        gamePackageName,
        marketName,
        env);

    Config config = configRepository.get(gamePackageName, env, marketName);

    return new PlayerRankingWithTimeData(
        PlayerRankMapper
            .toPlayerXpTimeRankData(playerXpWithTimes, gamePackageName, env, marketName),
        endTime(config.getConfigData().getXpResetInWeekCron()),
        ownScore);
  }

  @Cacheable(value = "base", key = "{ #root.targetClass.simpleName, "
      + "#root.methodName, #topNumber, #gamePackageName, #env,#marketName}", sync = true)
  @Override
  public List<PlayerXpWithTime> ByXpLastSevenDaysWithCache(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env) {

    return leaderBoardRepository.byXpLastSevenDays(topNumber,
        gamePackageName,
        marketName,
        env);
  }

  @Cacheable(value = "base", key = "{ #root.targetClass.simpleName, "
      + "#root.methodName, #topNumber, #gamePackageName, #env,#marketName,#uuid}", sync = true)
  @Override
  public PlayerRankingWithTimeData byMostRefralLastSevenDays(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env,
      String uuid) {
    int ownScore = -1;
    if (uuid != null) {
      PlayerRefral playerRefral = playerRefralRepository
          .find(uuid, gamePackageName, env, marketName);
      if (playerRefral != null) {
        PlayerRefralData playerRefralData = playerRefral.getPlayerRefrals()
            .get(fix(gamePackageName))
            .get(env)
            .get(marketName);
        ownScore = playerRefralData.getQuantity();
      }
    }

    if (topNumber == null) {
      topNumber = 100;
    }
    if (topNumber == 0) {
      topNumber = 100;
    }
    if (marketName == null) {
      marketName = "all-markets";
    }

    List<PlayerRefral> playerRefrals = leaderBoardRepository.byMostRefralLastSevenDays(topNumber,
        gamePackageName,
        marketName,
        env);

    if (playerRefrals == null) {
      return new PlayerRankingWithTimeData(new ArrayList<>(), 0, 0);
    }

    if (playerRefrals.size() == 0) {
      return new PlayerRankingWithTimeData(new ArrayList<>(), 0, 0);
    }
    Config config = configRepository.get(gamePackageName, env, marketName);

    return new PlayerRankingWithTimeData(
        PlayerRankMapper.toPlayerRefralRank(playerRefrals, gamePackageName, env, marketName),
        endTime(config.getConfigData().getReferalResetDayInWeekCron()),
        ownScore);
  }

  @Cacheable(value = "base", key = "{ #root.targetClass.simpleName, "
      + "#root.methodName, #topNumber, #gamePackageName, #env,#marketName}", sync = true)
  @Override
  public PlayerRankingWithTimeData theMostBuyerGem(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env) {

    if (topNumber == null) {
      topNumber = 100;
    }
    if (topNumber == 0) {
      topNumber = 100;
    }
    if (marketName == null) {
      marketName = "all-markets";
    }

    List<PlayerGemBuy> playerGemBuys = leaderBoardRepository.theMostBuyerGem(topNumber,
        gamePackageName,
        marketName,
        env);

    if (playerGemBuys == null) {
      return new PlayerRankingWithTimeData(new ArrayList<>(), 0, 0);
    }
    if (playerGemBuys.size() == 0) {
      return new PlayerRankingWithTimeData(new ArrayList<>(), 0, 0);
    }

    Config config = configRepository.get(gamePackageName, env, marketName);

    return new PlayerRankingWithTimeData(
        PlayerRankMapper.toPlayerGemRank(playerGemBuys, gamePackageName, env, marketName),
        endTime(config.getConfigData().getGemResetDayInWeekCron()),
        -1);
  }

  //for add every week first person and delete others
  @Override
  public PlayerHistoryGemDailyInfo theMostWeeklyHistoryGem(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env,
      String uuid) {

    int ownScore = -1;
    long ownDate = -1;

    if (uuid != null) {
      PlayerGemBuyHistoryTime playerGemBuyHistoryTime = playerGemBuyHistoryTimeRepository
          .get(uuid, gamePackageName, env, marketName);
      if (playerGemBuyHistoryTime != null) {
        PlayerGemBuyData playerGemBuyData = playerGemBuyHistoryTime.getPlayerMostBuyGem()
            .get(fix(gamePackageName))
            .get(env)
            .get(marketName);

        ownScore = playerGemBuyData.getGem();
        ownDate = playerGemBuyData.getDate();
      }
    }

    if (topNumber == null) {
      topNumber = 100;
    }
    if (topNumber == 0) {
      topNumber = 100;
    }

    List<PlayerGemBuyHistoryTime> leaderBoard = theMostWeeklyHistoryGemWithCache(
        topNumber,
        gamePackageName,
        marketName,
        env

    );

    return new PlayerHistoryGemDailyInfo(
        PlayerRankMapper.toGemByHistoryRank(
            leaderBoard,
            gamePackageName,
            env,
            marketName
        ),
        ownDate,
        ownScore

    );

  }

  @Cacheable(value = "base", key = "{ #root.targetClass.simpleName, "
      + "#root.methodName, #topNumber, #gamePackageName, #env,#marketName}", sync = true)
  @Override
  public List<PlayerGemBuyHistoryTime> theMostWeeklyHistoryGemWithCache(
      Integer topNumber,
      String gamePackageName,
      String marketName,
      String env) {

    return playerGemBuyHistoryTimeRepository.getLeaderBoard(
        topNumber,
        gamePackageName,
        env,
        marketName
    );
  }

  ////////////////////////////////////////////
  ////////////private methods/////////////
  ///////////////////////////////////////
  private void calculationJobs(Config config) {
    FCMGameMessageInfoGetAll allMessageBy = notificationFeignService.getAllMessageBy(
        config.getGamePackageName(),
        config.getEnv(),
        config.getMarketName()
    );
    List<FCMGameMessageInfo> fcmGameMessageInfos = allMessageBy.getFcmGameMessageInfos();
    LeaderBoardRewardInfo allPrizes = leaderBoardRewardService.findBy(
        config.getGamePackageName(),
        config.getEnv(),
        config.getMarketName()
    );

    Calendar c = Calendar.getInstance();
    c.setTime(c.getTime());
    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
    ConfigData configData = config.getConfigData();
    ////////0 ---> all day
    ////////1 ---> sunday
    ////////2 ---> monday
    ////////3 ---> tuesday
    ////////4 ---> wednesday
    ////////5 ---> thursday
    ////////6 ---> friday
    ////////7 ---> saturday
    if (configData.getGemResetDayInWeekCron() == 0
        || configData.getGemResetDayInWeekCron() == dayOfWeek) {
      List<WalletChange> walletChanges = allPrizes.getRewards()
          .get(LeaderBoardType.GEM_BUY);

      List<PlayerGemBuy> playerGemBuys = leaderBoardRepository
          .theMostBuyerGem(walletChanges.size(),
              config.getGamePackageName(),
              config.getMarketName(),
              config.getEnv());

      for (int i = 0; i < walletChanges.size(); i++) {
        ///send notification of prizes to user////////
        if (playerGemBuys.size() <= i) {
          break;
        }
        Stream<FCMGameMessageInfo> fcmGameMessageInfoStream = fcmGameMessageInfos.stream()
            .filter(e -> e.getEnumKeyFCM().equals(EnumKeyFCM.MOST_GEM_BUY));
        Optional<FCMGameMessageInfo> fcmGameMessageInfo = fcmGameMessageInfoStream.findAny();
        userFeignService.addNotificationToOneUser(
            new AddNotificationToOneUserModel(
                fcmGameMessageInfo.get().getTitle().replaceAll("i", String.valueOf(i + 1)),
                null,
                true,
                walletChanges.get(i).getGem(),
                walletChanges.get(i).getGold(),
                walletChanges.get(i).getLevel(),
                walletChanges.get(i).getXp(),
                walletChanges.get(i).isHamiAded(),
                walletChanges.get(i).getAvatarIds(),
                walletChanges.get(i).getAddedVipPeriodTime(),
                playerGemBuys.get(i).getUserUuId(),
                fcmGameMessageInfo.get().getTitle().replaceAll("i", String.valueOf(i + 1)),
                fcmGameMessageInfo.get().getMessage().replaceAll("i", String.valueOf(i + 1)),
                fcmGameMessageInfo.get().getTopic().replaceAll("i", String.valueOf(i + 1)),
                HamiAndNotificationType.MOST_GEM_BUY

            ),
            config.getGamePackageName(),
            config.getEnv(),
            config.getMarketName()
        );

      }
      if (playerGemBuys.size() != 0) {
        playerGemBuyHistoryTimeRepository.add(
            new PlayerGemBuyHistoryTime(
                playerGemBuys.get(0).getUserUuId(),
                playerGemBuys.get(0).getPlayerGaemBuy(),
                timeService.getNowUnixFromInstantClass())
        );
      }

      playerGemBuyRepository.reset(
          config.getGamePackageName(),
          config.getEnv(),
          config.getMarketName()
      );

    }

    if (configData.getReferalResetDayInWeekCron() == 0
        || configData.getReferalResetDayInWeekCron() == dayOfWeek) {

      List<WalletChange> walletChanges = allPrizes.getRewards()
          .get(LeaderBoardType.REFRAL);

      List<PlayerRefral> playerRefrals = leaderBoardRepository.byMostRefralLastSevenDays(
          walletChanges.size(),
          config.getGamePackageName(),
          config.getMarketName(),
          config.getEnv()
      );

      for (int i = 0; i < walletChanges.size(); i++) {
        if (playerRefrals.size() <= i) {
          break;
        }
        ///send notification of prizes to user////////
        Stream<FCMGameMessageInfo> fcmGameMessageInfoStream = fcmGameMessageInfos.stream()
            .filter(e -> e.getEnumKeyFCM().equals(EnumKeyFCM.FIRST_REFERRAL));
        Optional<FCMGameMessageInfo> fcmGameMessageInfo = fcmGameMessageInfoStream.findAny();

        userFeignService.addNotificationToOneUser(
            new AddNotificationToOneUserModel(
                fcmGameMessageInfo.get().getTitle().replaceAll("i", String.valueOf(i + 1)),
                null,
                true,
                walletChanges.get(i).getGem(),
                walletChanges.get(i).getGold(),
                walletChanges.get(i).getLevel(),
                walletChanges.get(i).getXp(),
                walletChanges.get(i).isHamiAded(),
                walletChanges.get(i).getAvatarIds(),
                walletChanges.get(i).getAddedVipPeriodTime(),
                playerRefrals.get(i).getUserUuId(),
                fcmGameMessageInfo.get().getTitle().replaceAll("i", String.valueOf(i + 1)),
                fcmGameMessageInfo.get().getMessage().replaceAll("i", String.valueOf(i + 1)),
                fcmGameMessageInfo.get().getTopic().replaceAll("i", String.valueOf(i + 1)),
                HamiAndNotificationType.MOST_REFERRAL

            ),
            config.getGamePackageName(),
            config.getEnv(),
            config.getMarketName()
        );
      }

      playerRefralRepository.reset(
          config.getGamePackageName(),
          config.getEnv(),
          config.getMarketName()
      );
    }

    if (configData.getXpResetInWeekCron() == 0
        || configData.getXpResetInWeekCron() == dayOfWeek) {
      List<WalletChange> walletChanges = allPrizes.getRewards()
          .get(LeaderBoardType.XP_TIME);
      List<PlayerXpWithTime> playerXpWithTimes = leaderBoardRepository.byXpLastSevenDays(
          walletChanges.size(),
          config.getGamePackageName(),
          config.getMarketName(),
          config.getEnv());

      userFeignService.incXpWeeklyFirstPlace(
          config.getGamePackageName(),
          playerXpWithTimes.get(0).getUserUuId(),
          config.getEnv(),
          config.getMarketName()
      );

      for (int i = 0; i < walletChanges.size(); i++) {
        ///send notification of prizes to user////////
        if (playerXpWithTimes.size() <= i) {
          break;
        }
        Stream<FCMGameMessageInfo> fcmGameMessageInfoStream = fcmGameMessageInfos.stream()
            .filter(e -> e.getEnumKeyFCM().equals(EnumKeyFCM.BEST_XP_TIME));
        Optional<FCMGameMessageInfo> fcmGameMessageInfo = fcmGameMessageInfoStream.findAny();

        userFeignService.addNotificationToOneUser(
            new AddNotificationToOneUserModel(
                fcmGameMessageInfo.get().getTitle().replaceAll("i", String.valueOf(i + 1)),
                null,
                true,
                walletChanges.get(i).getGem(),
                walletChanges.get(i).getGold(),
                walletChanges.get(i).getLevel(),
                walletChanges.get(i).getXp(),
                walletChanges.get(i).isHamiAded(),
                walletChanges.get(i).getAvatarIds(),
                walletChanges.get(i).getAddedVipPeriodTime(),
                playerXpWithTimes.get(i).getUserUuId(),
                fcmGameMessageInfo.get().getTitle().replaceAll("i", String.valueOf(i + 1)),
                fcmGameMessageInfo.get().getMessage().replaceAll("i", String.valueOf(i + 1)),
                fcmGameMessageInfo.get().getTopic().replaceAll("i", String.valueOf(i + 1)),
                HamiAndNotificationType.BEST_XP_TIME

            ),
            config.getGamePackageName(),
            config.getEnv(),
            config.getMarketName()
        );
      }

      leaderBoardMemoryRepository.deleteSpecificKey(
          config.getGamePackageName(),
          config.getEnv(),
          config.getMarketName(),
          "xp_weekly"
      );
      playerXpRepository.resetXpsWithTime(
          config.getGamePackageName(),
          config.getEnv(),
          config.getMarketName()
      );
    }
  }

  private String findMarketName(String challengeId, String marketName) {

    Object o = challengeFeignService.isIncludeAllMarket(challengeId);

    /*if (((LinkedHashMap) o).get("data").equals(true)) {
      marketName = defaultMarket;
    }*/
    if (o.equals(true)) {
      marketName = defaultMarket;
    }
    return marketName;
  }


  private long endTime(int dayOfWeek) {
    if (dayOfWeek == 0) {
      long MINUTES_IN_DAY = 24 * 60;
      ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Tehran"));

      zonedDateTime = zonedDateTime.plusMinutes(MINUTES_IN_DAY)
          .truncatedTo(ChronoUnit.DAYS);
      return zonedDateTime.toInstant().getEpochSecond();

    }
    return getNext(dayOfWeek).toDate().toInstant().getEpochSecond();
  }


  private LocalDate getNext(int dayOfWeek) {
    LocalDate today = new LocalDate();
    return getNext(dayOfWeek, today);
  }

  private LocalDate getNext(int dayOfWeek, LocalDate fromDate) {
    int dayOffset = DateTimeConstants.DAYS_PER_WEEK - dayOfWeek + 1;
    LocalDate weekContainingDay = fromDate.plusDays(dayOffset);
    return weekContainingDay.withDayOfWeek(dayOfWeek);
  }

  private String fix(String packageName) {
    return packageName.replace(".", "_");
  }
}
