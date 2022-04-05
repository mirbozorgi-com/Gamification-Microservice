package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.constant.UpdateStatus;
import com.mirbozorgi.business.domain.TimeAndVersioningInfo;
import com.mirbozorgi.business.domain.WalletChangeModelDailyReward;
import com.mirbozorgi.business.domain.WalletWithVipTime;
import com.mirbozorgi.business.domain.friendship.GetGameProfileForFriendShip;
import com.mirbozorgi.business.domain.game.AddGameToUserData;
import com.mirbozorgi.business.domain.user.AdvertisementSeeInfo;
import com.mirbozorgi.business.domain.user.UserGetGlobalResponse;
import com.mirbozorgi.business.domain.user.UserGetResponse;
import com.mirbozorgi.business.domain.user.UserGetWalletAndStatistic;
import com.mirbozorgi.business.domain.user.UserStatistic;
import com.mirbozorgi.business.exception.ApikeyException;
import com.mirbozorgi.business.exception.DailyRewardException;
import com.mirbozorgi.business.exception.LackGemException;
import com.mirbozorgi.business.exception.LackGoldException;
import com.mirbozorgi.business.exception.LackXpException;
import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.exception.NotificationNotFoundException;
import com.mirbozorgi.business.exception.SocialFollowBeforeException;
import com.mirbozorgi.business.exception.SocialNetworkNotFoundException;
import com.mirbozorgi.business.exception.TokenParsForUserServiceException;
import com.mirbozorgi.business.exception.UserHasAvatarException;
import com.mirbozorgi.business.exception.UserSeeAdvertisementException;
import com.mirbozorgi.business.exception.VipTypeException;
import com.mirbozorgi.business.mapper.UserMapper;
import com.mirbozorgi.business.mapper.WalletChangeModelMapper;
import com.mirbozorgi.business.service.StringService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.business.service.UserService;
import com.mirbozorgi.core.document.User;
import com.mirbozorgi.core.domain.NewsData;
import com.mirbozorgi.core.domain.NotificationData;
import com.mirbozorgi.core.domain.Statistic;
import com.mirbozorgi.core.domain.UserGameProfile;
import com.mirbozorgi.core.domain.Wallet;
import com.mirbozorgi.core.repository.document.ConfigRepository;
import com.mirbozorgi.core.repository.document.UserRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import mirbozorgi.base.constanct.PlayerType;
import mirbozorgi.base.domain.advertisement.PackageThirdPartyInfo;
import mirbozorgi.base.domain.cohort.ChooseCohortData;
import mirbozorgi.base.domain.cohort.GetConfigCohortData;
import mirbozorgi.base.domain.game.AvatarInfo;
import mirbozorgi.base.domain.game.AvatarType;
import mirbozorgi.base.domain.game.DailyRewardInfo;
import mirbozorgi.base.domain.game.GameInfo;
import mirbozorgi.base.domain.game.LevelInfo;
import mirbozorgi.base.domain.hami.CheckVipTimeInfo;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;
import mirbozorgi.base.domain.security.GetDeviceIdInfo;
import mirbozorgi.base.domain.user.CurrentLevelInfo;
import mirbozorgi.base.domain.user.GetUserNameData;
import mirbozorgi.base.domain.user.UserByXpGetResponse;
import mirbozorgi.base.domain.user.WalletChange;
import mirbozorgi.base.feignService.AdvertisementFeignService;
import mirbozorgi.base.feignService.CohortFeignService;
import mirbozorgi.base.feignService.GameFeignService;
import mirbozorgi.base.feignService.LeaderBoardFeignService;
import mirbozorgi.base.feignService.NotificationFeignService;
import mirbozorgi.base.feignService.ReferralFeignService;
import mirbozorgi.base.feignService.SecurityFeignService;
import mirbozorgi.base.feignService.UserHamiFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CohortFeignService cohortFeignService;


  @Autowired
  private LeaderBoardFeignService leaderBoardFeignService;

  @Autowired
  private TimeService timeService;

  @Autowired
  private GameFeignService gameFeignService;

  @Autowired
  private ConfigRepository configRepository;

  @Autowired
  private StringService stringService;


  @Autowired
  private UserHamiFeginService userHamiFeginService;


  @Autowired
  private AdvertisementFeignService advertisementFeignService;

  @Value("${market.default-name}")
  String defaultMarket;


  @Value("${cohort.default-name}")
  String defaultCohort;

  @Value("${api.key.walletchange}")
  String apiKeyWalletChange;

  @Autowired
  private ReferralFeignService referralFeignService;

  @Autowired
  private SecurityFeignService securityFeignService;

  @Autowired
  private NotificationFeignService notificationFeignService;

  @Override
  public void delete(String id) {
    User user = userRepository.findById(id);
    if (user == null) {
      throw new NotFoundException();
    }
    userRepository.delete(user);
  }

  @Override
  public UserGetResponse findById(String id) {
    return UserMapper.toGetResponse(userRepository.findById(id));
  }

  @Override
  public UserGetGlobalResponse findByUuId(
      String uuid,
      String gamePackageName,
      String env,
      String marketName) {

    gamePackageName = fix(gamePackageName);
    User user = userRepository.findByUuId(uuid);
    if (user == null) {
      throw new NotFoundException();
    }
    PlayerType playerType = user.getGameProfiles()
        .get(gamePackageName)
        .get(env)
        .get(marketName)
        .getPlayerType();
    return new UserGetGlobalResponse(
        user.getUuid(),
        user.getGameProfiles()
            .get(gamePackageName)
            .get(env)
            .get(marketName)
            .getClientVersion(),
        user.getGameProfiles()
            .get(gamePackageName)
            .get(env)
            .get(marketName)
            .getUsername(),
        playerType,
        user.getGameProfiles()
            .get(gamePackageName)
            .get(env)
            .get(marketName).getTokenFCM()

    );
  }

  @Override
  public List<UserGetResponse> findAll() {
    List<UserGetResponse> userGetResponses = new ArrayList<>();
    List<User> all = userRepository.findAll();

    for (User user : all) {
      userGetResponses.add(UserMapper.toGetResponse(user));
    }

    return userGetResponses;
  }


  @Override
  public List<UserByXpGetResponse> findAllByXp(
      int maxXp,
      int minXp,
      String packageName,
      String env,
      String market,
      String uuid) {
    List<UserByXpGetResponse> userGetResponses = new ArrayList<>();

    List<User> users = new ArrayList<>();
    users = userRepository.findAllByXp(maxXp, minXp, packageName, env, market, uuid);

    if (users.size() == 0) {
      return new ArrayList<>();
    }
    for (User user : users) {
      if (user.getUuid().equals(uuid)) {
        continue;
      }
      UserGameProfile userGameProfile = user.getGameProfiles()
          .get(fix(packageName))
          .get(env)
          .get(market);
      userGetResponses.add(new UserByXpGetResponse(
          user.getUuid(),
          userGameProfile.getUsername(),
          (int) userGameProfile.getWallet().getLevel(),
          userGameProfile.getWallet().getActiveAvatarIds()
      ));
    }
    return userGetResponses;
  }

  @Override
  public GetUserNameData getUserName(
      String uuid,
      String packageName,
      String env,
      String market
  ) {
    String userName = userRepository.getUserName(
        uuid,
        packageName,
        env,
        market
    );
    return new GetUserNameData(userName);
  }

  @Override
  public WalletChange walletChange(
      String apiKey,
      String uuidParam,
      String uuid,
      String packageName,
      String env,
      String market,
      int gem,
      int gold,
      Short level,
      Integer xp,
      List<Integer> currentActiveAvatarIds,
      int currentLevel,
      Boolean isGemBuy,
      long endVipTime) {
    if (!uuidParam.equals("")) {
      uuid = uuidParam;
    }
    if (!apiKey.equals(apiKeyWalletChange)) {
      throw new ApikeyException();
    }
    userRepository.walletChange(
        uuid,
        packageName,
        env,
        market,
        gem,
        gold,
        level,
        xp);
    UserGameProfile profile = userRepository.getUserGameProfileForFriendShip(
        uuid,
        packageName,
        env,
        market
    );

    String userName = "";
    try {
      userName = profile.getUsername();
    } catch (Exception ignored) {
    }
    if (isGemBuy) {
      leaderBoardFeignService.updateGem(
          uuid,
          gem,
          userName,
          packageName,
          env,
          market,
          currentLevel,
          currentActiveAvatarIds,
          endVipTime
      );
    }
    if (xp != 0) {
      leaderBoardFeignService.updateXp(
          uuid,
          xp,
          userName,
          packageName,
          env,
          market,
          currentLevel,
          currentActiveAvatarIds,
          endVipTime,
          profile.getWallet().getXp());

    }

    return new WalletChange(
        gem,
        gold,
        level,
        xp,
        false,
        new ArrayList<>(),
        0
    );
  }

  @Override
  public WalletWithVipTime getWallet(String uuid,
      String packageName,
      String env,
      String market) {
    Wallet wallet = userRepository.getWallet(
        uuid,
        packageName,
        env,
        market
    );
    long endVipTime = 0;
    try {
      CheckVipTimeInfo checkVipTimeInfo = userHamiFeginService.checkVipTime(uuid,
          packageName,
          env,
          market);
      endVipTime = checkVipTimeInfo.getFinishTime();
    } catch (Exception ignored) {
    }

    return new WalletWithVipTime(wallet, endVipTime);
  }

  @Override
  public WalletChange walletOfflineChange(
      String uuid,
      String packageName,
      String env,
      String market,
      int gem,
      int gold,
      int xp) {
    gem = -Math.abs(gem);
    gold = -Math.abs(gold);
    xp = -Math.abs(xp);

    Wallet wallet = userRepository.getWallet(
        uuid,
        packageName,
        env,
        market
    );

    if (wallet.getGem() < gem) {
      throw new LackGemException();
    }
    if (wallet.getGold() < gold) {
      throw new LackGoldException();
    }

    if (wallet.getXp() < xp) {
      throw new LackXpException();
    }

    userRepository.walletChange(
        uuid,
        packageName,
        env,
        market,
        gem,
        gold,
        (short) 0,
        xp);

    return new WalletChange(
        gem,
        gold,
        (short) 0,
        xp,
        false,
        new ArrayList<>(),
        0
    );
  }

  @Override
  public CurrentLevelInfo updateLevel(
      String uuid,
      String env,
      String packageName,
      String market) {
    UserGameProfile profile = userRepository.getUserGameProfileForFriendShip(
        uuid,
        packageName,
        env,
        market
    );
    LevelInfo levelInfo = gameFeignService.getCurrentLevel(
        packageName,
        env,
        profile.getWallet().getXp()

    );
    int level = levelInfo.getLevelNumber();
    if ((int) profile.getWallet().getLevel() == level) {
      return new CurrentLevelInfo(level);
    }

    int updatedLevel = userRepository.updateLevel(
        uuid,
        Short.valueOf(Integer.toString(level)),
        env,
        packageName,
        market
    );
    if (updatedLevel > 0) {
      return new CurrentLevelInfo(updatedLevel);

    }
    return new CurrentLevelInfo(level);

  }

  @Override
  public void setUserName(
      String username,
      String uuid,
      String packageName,
      String env,
      String market) {

    userRepository.setUserName(
        uuid,
        packageName,
        env,
        market,
        username
    );

  }

  @Transactional
  @Override
  public AddGameToUserData addOrUpdateGame(
      String uuid,
      String packageName,
      String env,
      String market,
      String cohortName,

      //profile data :
      boolean test,
      boolean debug,
      int clientVersion,
      String keyForClaimDailyContinueReward) {
    /////////////////////////////////
    // we set main market here because :
    // we need main and defaultMarkets both
    String marketForFirstAppOpen = market;
    GameInfo gameInfo = gameFeignService.getByPackageNameAndEnv(packageName, env);
    market = gameInfo.getDefaultMarketName();
    /////////////////////////////////////////////

    int defaultGem = 20;
    int defaultGold = 20;
    int defaultXp = 0;
    int defaultLevel = 0;

    if (uuid == null) {
      throw new TokenParsForUserServiceException();
    }

    if (uuid.equals("")) {
      throw new TokenParsForUserServiceException();
    }

    if (keyForClaimDailyContinueReward == null) {
      keyForClaimDailyContinueReward = "DEF";
    }
    WalletChangeModelDailyReward walletChangeModel = null;
    try {
      gameInfo = gameFeignService.getByPackageNameAndEnv(packageName, env);
      defaultGem = gameInfo.getDefaultGem();
      defaultGold = gameInfo.getDefaultGold();
      defaultXp = gameInfo.getDefaultXp();
      defaultLevel = gameInfo.getDefaultLevel();
    } catch (Exception ignored) {
    }

    User user = userRepository.findByUuId(uuid);

    if (user != null) {
      UserGameProfile userGameProfile = user.getGameProfiles().get(fix(packageName))
          .get(env)
          .get(market);

      //update clientVersion
      if (userGameProfile.getClientVersion() != clientVersion) {
        userRepository
            .updateClientVersion(uuid, clientVersion, env, packageName, market);
      }
      GetConfigCohortData getConfigCohortData = null;
      try {
        getConfigCohortData = cohortFeignService
            .getConfig(userGameProfile.getCohortName(), packageName, market);

      } catch (Exception e) {
        Object o = configRepository.getCohortConfig("cohort", defaultCohort);
        getConfigCohortData = new GetConfigCohortData(defaultCohort, o);
      }

      //add login to last login for get reward continue daily
      List<LocalDate> lastLogins = userGameProfile.getLastLogin();
      try {
        ArrayList allDailyContinues = gameFeignService
            .getAllDailyContinues(keyForClaimDailyContinueReward, packageName, env);

        List<WalletChangeModelDailyReward> walletChangeModels = WalletChangeModelMapper
            .toWalletChangeModel(allDailyContinues, false);

        if (!lastLogins.contains(LocalDate.now())) {
          if (allDailyContinues.size() - lastLogins.size() == 1 && lastLogins
              .contains(LocalDate.now().plusDays(-1))) {
            userRepository.resetLastLogin(uuid, packageName, env, market);
            walletChangeModel = new WalletChangeModelDailyReward(
                walletChangeModels.get(walletChangeModels.size() - 1).getGem(),
                walletChangeModels.get(walletChangeModels.size() - 1).getGold(),
                walletChangeModels.get(walletChangeModels.size() - 1).getLevel(),
                walletChangeModels.get(walletChangeModels.size() - 1).getXp(),
                false,
                walletChangeModels.get(walletChangeModels.size() - 1).isHamiAdded(),
                walletChangeModels.get(walletChangeModels.size() - 1).getVipTimeAdded(),
                walletChangeModels.get(walletChangeModels.size() - 1).getAvatarIds()

            );
            walletChangeModel.setDayNumber(walletChangeModels.size());

          } else if (lastLogins.size() == 0) {
            userRepository.addLastLogin(uuid, packageName, env, market, LocalDate.now());
            walletChangeModel = new WalletChangeModelDailyReward(
                walletChangeModels.get(0).getGem(),
                walletChangeModels.get(0).getGold(),
                walletChangeModels.get(0).getLevel(),
                walletChangeModels.get(0).getXp(),
                false,
                walletChangeModels.get(0).isHamiAdded(),
                walletChangeModels.get(0).getVipTimeAdded(),
                walletChangeModels.get(0).getAvatarIds()

            );
            walletChangeModel.setDayNumber(1);


          } else if (lastLogins.get(lastLogins.size() - 1).equals(LocalDate.now().plusDays(-1))) {
            userRepository.addLastLogin(uuid, packageName, env, market, LocalDate.now());

            walletChangeModel = new WalletChangeModelDailyReward(
                walletChangeModels.get(lastLogins.size()).getGem(),
                walletChangeModels.get(lastLogins.size()).getGold(),
                walletChangeModels.get(lastLogins.size()).getLevel(),
                walletChangeModels.get(lastLogins.size()).getXp(),
                false,
                walletChangeModels.get(lastLogins.size()).isHamiAdded(),
                walletChangeModels.get(lastLogins.size()).getVipTimeAdded(),
                walletChangeModels.get(lastLogins.size()).getAvatarIds()

            );
            walletChangeModel.setDayNumber(lastLogins.size());
          } else {
            userRepository.resetLastLogin(uuid, packageName, env, market);
            userRepository.addLastLogin(uuid, packageName, env, market, LocalDate.now());
            walletChangeModel = new WalletChangeModelDailyReward(
                walletChangeModels.get(0).getGem(),
                walletChangeModels.get(0).getGold(),
                walletChangeModels.get(0).getLevel(),
                walletChangeModels.get(0).getXp(),
                false,
                walletChangeModels.get(0).isHamiAdded(),
                walletChangeModels.get(0).getVipTimeAdded(),
                walletChangeModels.get(0).getAvatarIds()
            );
            walletChangeModel.setDayNumber(1);
          }
          if (walletChangeModel.getGem() != 0 || walletChangeModel.getGold() != 0
              || walletChangeModel.getXp() != 0) {
            userRepository.walletChange(
                uuid,
                packageName,
                env,
                market,
                walletChangeModel.getGem(),
                walletChangeModel.getGold(),
                walletChangeModel.getLevel(),
                walletChangeModel.getXp()
            );
          }
          if (walletChangeModel.isHamiAdded()) {
            userHamiFeginService.addHami(
                uuid,
                userGameProfile.getUsername(),
                "",
                null,
                HamiAndNotificationType.DAILY_REWARD,
                packageName,
                env,
                market
            );
          }

          if (walletChangeModel.getVipTimeAdded() != 0) {
            userHamiFeginService.addVipUser(
                uuid,
                timeService.getNowUnixFromInstantClass(),
                walletChangeModel.getVipTimeAdded() + timeService.getNowUnixFromInstantClass(),
                walletChangeModel.getVipTimeAdded(),
                packageName,
                env,
                market
            );
          }
          if (walletChangeModel.getAvatarIds() != null) {
            if (walletChangeModel.getAvatarIds().size() != 0) {
              addAvatarPurchase(
                  uuid,
                  packageName,
                  env,
                  market,
                  walletChangeModel.getAvatarIds()
              );
            }
          }

        }
      } catch (Exception ignored) {
      }
      long yearPassedFromRegister = ChronoUnit.YEARS.between(
          userGameProfile.getRegisterDate(),
          LocalDate.now());
      int intPassedYear = Integer.parseInt(String.valueOf(yearPassedFromRegister));
      if (intPassedYear != userGameProfile.getStatistic().getYearPassedFromRegister()) {
        userRepository.updateYearPassedRegister(
            uuid,
            packageName,
            env,
            market,
            intPassedYear
        );
      }

      //////////////////////end reward continue daily//////////////////

      long endTimeVip = 0;

      try {
        CheckVipTimeInfo checkVipTimeInfo = userHamiFeginService.checkVipTime(
            uuid,
            packageName,
            env,
            market
        );
        endTimeVip = checkVipTimeInfo.getFinishTime();
      } catch (Exception ignored) {
      }

      userRepository.updateMarketForFirstAppOpen(
          uuid, packageName, env, market, marketForFirstAppOpen);
      return new AddGameToUserData(
          userGameProfile,
          getConfigCohortData.getConfig(),
          timeService.getNowUnixFromInstantClass(),
          checkUpdate(clientVersion, gameInfo),
          walletChangeModel,
          endTimeVip);


    }

    //////////////new user///////////////////////////

    String cohortNameFounded = "";
    //cohort assigning :
    try {
      ChooseCohortData chooseCohortData = cohortFeignService
          .chooseCohort(clientVersion, packageName, env, market, cohortName);
      cohortNameFounded = chooseCohortData.getCohortName();

    } catch (Exception e) {
      cohortNameFounded = defaultCohort;
    }

    List<LocalDate> localDates = new ArrayList<>();
    localDates.add(LocalDate.now());
    Wallet wallet = new Wallet(
        defaultGem,
        defaultGold,
        Short.parseShort(String.valueOf(defaultLevel)),
        defaultXp,
        new ArrayList<>(),
        new ArrayList<>());
    Statistic statistic = new Statistic(
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        new HashMap<>());
    UserGameProfile userGameProfile = new UserGameProfile(
        cohortNameFounded,
        true,
        "",
        test,
        debug,
        false,
        clientVersion,
        timeService.getNowUnixFromInstantClass(),
        "",
        true,
        wallet,
        statistic,
        new HashMap<>(),
        PlayerType.ORDINARY,
        new HashMap<>(),
        LocalDate.now().plusDays(-10),
        localDates,
        LocalDate.now(),
        new HashMap<>(),
        false,
        null,
        marketForFirstAppOpen
    );

    UserGameProfile userGameProfileNew = userRepository.addGame(
        uuid,
        packageName,
        env,
        market,
        userGameProfile
    );

    //////////add referral codes to new user ///////////////////////////
    String deviceId = "";
    try {
      GetDeviceIdInfo getDeviceIdInfo = securityFeignService.getDeviceId(uuid,
          packageName,
          marketForFirstAppOpen,
          env);
      deviceId = getDeviceIdInfo.getDeviceId();

    } catch (Exception ignored) {
    }
    referralFeignService.addUserReferral(
        deviceId,
        packageName,
        env,
        uuid,
        market
    );

    Object config = null;
    try {
      config = cohortFeignService
          .getConfig(cohortNameFounded, packageName, market);

    } catch (Exception e) {
      config = configRepository.getConfig("cohort").getConfigs().get("DEF");
    }

    return new AddGameToUserData(
        userGameProfileNew,
        config,
        timeService.getNowUnixFromInstantClass(),
        checkUpdate(clientVersion, gameInfo),
        walletChangeModel,
        0

    );
  }

  @Override
  public String setTokenFCM(
      String uuid,
      String packageName,
      String env,
      String market,
      String tokenFCM) {

    if (tokenFCM != null) {
      userRepository.updateTokenFCM(
          uuid,
          packageName,
          env,
          market,
          tokenFCM
      );
    }
    return tokenFCM;
  }

  @Override
  public TimeAndVersioningInfo getTimeAndVersion(
      String packageName,
      String env,
      int clientVersion) {

    GameInfo gameInfo = null;
    try {
      gameInfo = gameFeignService.getByPackageNameAndEnv(packageName, env);
    } catch (Exception ignored) {
    }

    return new TimeAndVersioningInfo(
        timeService.getNowUnixFromInstantClass(),
        checkUpdate(clientVersion, gameInfo)
    );

  }

  @Override
  public void updateUnreadMassage(
      String uuid,
      int unreadMassage,
      String env,
      String packageName,
      String market) {
    userRepository.updateUnreadMassage(uuid, unreadMassage, env, packageName, market);
  }

  @Override
  public void resetUnreadMassage(String uuid, String env, String packageName,
      String market) {
    userRepository.resetUnreadMassage(uuid, env, packageName, market);

  }


  @Override
  public NotificationData addNotificationToAllUser(
      String gamePackageName,
      String env,
      String marketName,
      String name,
      String notificationId,
      Object config,
      int minClientVersion,
      int maxClientVersion,
      Boolean removeAble,
      int gem,
      int gold,
      Short level,
      int xp,
      boolean hamiAded,
      List<Integer> avatarIds,
      long addedVipPeriodTime,
      String title,
      String message,
      String topic,
      HamiAndNotificationType type,
      Boolean isFcmSend) {

    List<User> users = userRepository.getAllUuIdForFCM(
        gamePackageName,
        env,
        marketName,
        minClientVersion,
        maxClientVersion
    );
    if (isFcmSend) {
      for (User user : users) {
        try {
          String tokenFCM = user.getGameProfiles()
              .get(fix(gamePackageName))
              .get(env)
              .get(marketName)
              .getTokenFCM();
          notificationFeignService.byToken(
              title,
              message,
              topic,
              tokenFCM,
              gamePackageName,
              marketName,
              env);
        } catch (Exception ignored) {
        }

      }

    }
    return userRepository.addNotificationToAllUser(
        gamePackageName,
        env,
        marketName,
        minClientVersion,
        maxClientVersion,
        new NotificationData(
            notificationId,
            name,
            config,
            timeService.getNowUnixFromInstantClass(),
            removeAble,
            new WalletChange(
                gem,
                gold,
                level,
                xp,
                hamiAded,
                avatarIds,
                addedVipPeriodTime
            ),
            type)
    );

  }

  @Override
  public NotificationData addNotificationToOneUser(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      String name,
      Object config,
      Boolean removeAble,
      int gem,
      int gold,
      Short level,
      int xp,
      boolean hamiAded,
      List<Integer> avatarIds,
      long addedVipPeriodTime,
      String title,
      String message,
      String topic,
      HamiAndNotificationType type) {
    String notificationId = stringService.generateRandomString(true, true, 10);

    UserGetGlobalResponse user = findByUuId(uuid,
        gamePackageName,
        env,
        marketName);

    try {
      notificationFeignService.byToken(
          title,
          message,
          topic,
          user.getTokenFCM(),
          gamePackageName,
          marketName,
          env
      );
    } catch (Exception ignored) {
    }

    return userRepository.addNotificationToOneUser(
        gamePackageName,
        env,
        marketName,
        new NotificationData(
            notificationId,
            name,
            config,
            timeService.getNowUnixFromInstantClass(),
            removeAble,
            new WalletChange(
                gem,
                gold,
                level,
                xp,
                hamiAded,
                avatarIds,
                addedVipPeriodTime
            ),
            type),
        uuid);

  }

  @Override
  public Object popNotification(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      String notificationId) {
    WalletChange walletChange = null;
    NotificationData notification = null;
    notification = userRepository.findNotification(
        uuid,
        gamePackageName,
        env,
        marketName,
        notificationId
    );

    if (notification == null) {
      throw new NotificationNotFoundException();

    }
    walletChange = notification.getWalletChange();
    userRepository.popNotification(
        uuid,
        gamePackageName,
        env,
        marketName,
        notificationId);
    if (walletChange != null) {

      /////wallet changing ////////////////
      if (notification.getRemoveAble()) {
        if (walletChange.getGem() != 0 || walletChange.getXp() != 0
            || walletChange.getGold() != 0) {
          userRepository.walletChange(
              uuid,
              gamePackageName,
              env,
              marketName,
              walletChange.getGem(),
              walletChange.getGold(),
              Short.parseShort("0"),
              walletChange.getXp()
          );
        }
        if (walletChange.isHamiAded()) {
          UserGetGlobalResponse user = findByUuId(
              uuid,
              gamePackageName,
              env,
              marketName
          );
          userHamiFeginService.addHami(
              uuid,
              user.getUsername(),
              notification.getType().toString(),
              null,
              notification.getType(),
              gamePackageName,
              env,
              marketName);
        }
        if (walletChange.getAvatarIds() != null) {
          if (walletChange.getAvatarIds().size() != 0) {
            addAvatarPurchase(
                uuid,
                gamePackageName,
                env,
                marketName,
                walletChange.getAvatarIds()
            );
          }
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
        return new WalletChange(
            walletChange.getGem(),
            walletChange.getGold(),
            walletChange.getLevel(),
            walletChange.getXp(),
            walletChange.isHamiAded(),
            walletChange.getAvatarIds(),
            walletChange.getAddedVipPeriodTime()
        );
      }
    }

    return userRepository.addNews(uuid, gamePackageName, env, marketName, new NewsData(
        notification.getNotificationId(),
        notification.getName(),
        notification.getConfig(),
        notification.getCreationDate(),
        true
    ));


  }


  @Override
  public GetGameProfileForFriendShip getGameProfileForFriendShip(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String uuidParam
  ) {
    if (uuidParam != null) {
      if (!uuidParam.equals("")) {
        uuid = uuidParam;
      }
    }
    UserGameProfile userGameProfile = userRepository.getUserGameProfileForFriendShip(
        uuid,
        packageName,
        env,
        marketName
    );
    if (userGameProfile == null) {
      throw new NotFoundException();
    }
    long endTimeVip = 0;
    try {
      CheckVipTimeInfo checkVipTimeInfo = userHamiFeginService.checkVipTime(
          uuid,
          packageName,
          env,
          marketName
      );
      endTimeVip = checkVipTimeInfo.getFinishTime();

    } catch (Exception ignored) {
    }

    return new GetGameProfileForFriendShip(
        userGameProfile.getUsername(),
        userGameProfile.getWallet(),
        uuid,
        userGameProfile.getStatistic(),
        endTimeVip);

  }

  @Override
  public void addNews(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String name,
      Object config) {
    String id = stringService.generateRandomString(true, true, 20);
    userRepository.addNews(
        uuid,
        packageName,
        env,
        marketName,
        new NewsData(
            id,
            name,
            config,
            timeService.getNowUnixFromInstantClass(),
            false
        ));
  }

  @Override
  public void readNews(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String id) {
    userRepository.readNews(uuid, packageName, env, marketName, id);

  }


  @Override
  public void addAvatarPurchase(
      String uuid,
      String packageName,
      String env,
      String marketName,
      List<Integer> avatarIds) {

    for (Integer avatarId : avatarIds) {
      AvatarInfo avatarInfo = gameFeignService.getAvatarById(avatarId);
      if (avatarInfo == null) {
        throw new NotFoundException();
      }

      UserGameProfile userGameProfile = userRepository.getUserGameProfileForFriendShip(
          uuid,
          packageName,
          env,
          marketName
      );
      Wallet wallet = userGameProfile.getWallet();
      if (wallet.getPurchasedAvatars().contains(avatarId)) {
        throw new UserHasAvatarException();
      }

      if (avatarInfo.getTypeForBuy().equals(AvatarType.VIP)) {
        boolean isVip = userHamiFeginService.checkVip(
            uuid,
            packageName,
            env,
            marketName
        );
        if (isVip) {
          userRepository.addAvatar(
              uuid,
              packageName,
              env,
              marketName,
              avatarId
          );
        } else {
          throw new VipTypeException();
        }
        return;
      } else if (avatarInfo.getTypeForBuy().equals(AvatarType.GEM)) {

        if (wallet.getGem() - avatarInfo.getAmount().intValue() < 0) {
          throw new LackGemException();
        }

        userRepository.walletChange(
            uuid,
            packageName,
            env,
            marketName,
            -avatarInfo.getAmount().intValue(),
            0,
            Short.valueOf("0"),
            0
        );
      }

      userRepository.addAvatar(
          uuid,
          packageName,
          env,
          marketName,
          avatarId
      );
    }

  }

  @Override
  public void setAvatarId(
      String uuid,
      String packageName,
      String env,
      String marketName,
      List<Integer> avatarIds) {
    List<Integer> avatarIdsFinal = new ArrayList<>();
    Wallet wallet = userRepository.getUserGameProfileForFriendShip(
        uuid,
        packageName,
        env,
        marketName
    ).getWallet();

    for (Integer avatarId : avatarIds) {
      if (wallet.getPurchasedAvatars().contains(avatarId)) {
        avatarIdsFinal.add(avatarId);
      }

      //for free avatar Ids
      else if (gameFeignService.getAvatarById(avatarId).getFree()) {
        avatarIdsFinal.add(avatarId);

        userRepository.addAvatar(
            uuid,
            packageName,
            env,
            marketName,
            avatarId
        );
      }
    }

    userRepository.setAvatar(
        uuid,
        packageName,
        env,
        marketName,
        avatarIdsFinal
    );

  }

  @Override
  public WalletChange useDailyReward(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String username) {
    DailyRewardInfo dailyRewardInfo = gameFeignService.getDailyRewardBy(packageName, env);
    LocalDate now = LocalDate.now();

    Boolean bool = userRepository.useDailyReward(
        now,
        uuid,
        packageName,
        env,
        marketName
    );

    if (!bool) {
      throw new DailyRewardException();
    }

    if (dailyRewardInfo.getGem() != 0 ||
        dailyRewardInfo.getXp() != 0 ||
        dailyRewardInfo.getGold() != 0 ||
        dailyRewardInfo.getLevel() != null) {
      userRepository.walletChange(
          uuid,
          packageName,
          env,
          marketName,
          dailyRewardInfo.getGem(),
          dailyRewardInfo.getGold(),
          dailyRewardInfo.getLevel(),
          dailyRewardInfo.getXp()
      );
    }

    if (dailyRewardInfo.getVipPeriodTime() != 0) {
      userHamiFeginService.addVipUser(
          uuid,
          timeService.getNowUnixFromInstantClass(),
          timeService.getNowUnixFromInstantClass() + dailyRewardInfo.getVipPeriodTime(),
          dailyRewardInfo.getVipPeriodTime(),
          packageName,
          env,
          marketName
      );
    }

    if (dailyRewardInfo.getAddHami()) {
      userHamiFeginService.addHami(
          uuid,
          username,
          "",
          null,
          HamiAndNotificationType.FREE_DAILY,
          packageName,
          env,
          marketName

      );
    }

    List<Integer> avatarIds = new ArrayList<>();
    avatarIds.add(Integer.valueOf(String.valueOf(dailyRewardInfo.getAvatarId())));
    return new WalletChange(
        dailyRewardInfo.getGem(),
        dailyRewardInfo.getGold(),
        dailyRewardInfo.getLevel(),
        dailyRewardInfo.getXp(),
        dailyRewardInfo.getAddHami(),
        avatarIds,
        dailyRewardInfo.getVipPeriodTime()
    );
  }

  @Override
  public void updateUserStatistic(
      String uuid,
      String packageName,
      String env,
      String market,
      int inchamiCount,
      int incGameNumber,
      int incInvitation,
      int incGemSpent,
      int incWin,
      int incLose
  ) {

    userRepository.updateUserStatistic(
        uuid,
        packageName,
        env,
        market,
        inchamiCount,
        incGameNumber,
        incInvitation,
        incGemSpent,
        incWin,
        incLose);


  }

  @Override
  public void updateFirstWeeklyXp(
      String uuid,
      String packageName,
      String env,
      String market,
      int quantity) {

    userRepository.updateFirstWeeklyXp(
        uuid,
        packageName,
        env,
        market,
        quantity
    );
  }

  @Override
  public UserStatistic getUserStatistic(
      String uuid,
      String packageName,
      String env,
      String market) {

    return UserMapper.toGetUserStatistic(userRepository.getUserStatistic(
        uuid,
        packageName,
        env,
        market));
  }

  @Override
  public UserGetWalletAndStatistic getWalletAndStatistic(
      String uuid,
      String packageName,
      String env,
      String market) {

    UserGameProfile userWalletAndStatistic = userRepository.getUserWalletAndStatistic(
        uuid,
        packageName,
        env,
        market
    );
    return UserMapper.toGetUserWalletAndStatistic(userWalletAndStatistic.getWallet(),
        userWalletAndStatistic.getStatistic());
  }

  @Override
  public WalletChange addSocialNetworkFollowToProfile(
      String uuid,
      String username,
      String packageName,
      String env,
      String marketName,
      String nameOfSocialNetwork,
      List<Integer> currentActiveAvatarIds,
      int currentLevel,
      long endVipTime) {
    HashMap allNetworks = gameFeignService.getAllNetworks(packageName, env);
    Map<String, Boolean> allSocialNetworkFollowedByUser = userRepository
        .getAllSocialNetworkFollowedByUser(
            uuid,
            packageName,
            env,
            marketName
        );
    if (!allNetworks.keySet().toString().contains(nameOfSocialNetwork)) {
      throw new SocialNetworkNotFoundException();
    }

    if (allSocialNetworkFollowedByUser != null) {
      if (allSocialNetworkFollowedByUser.keySet().toString().contains(nameOfSocialNetwork)) {
        throw new SocialFollowBeforeException();
      }
    }
    Object o = allNetworks.get(nameOfSocialNetwork);
    WalletChange walletChange = WalletChangeModelMapper.toWalletChange(o);
    userRepository.addFollowSocialNetworks(
        uuid,
        packageName,
        env,
        marketName,
        nameOfSocialNetwork
    );
    /////wallet changing ////////////////
    if (walletChange.getGem() != 0 || walletChange.getXp() != 0 || walletChange.getGold() != 0) {
      walletChange(
          apiKeyWalletChange,
          "",
          uuid,
          packageName,
          env,
          marketName,
          walletChange.getGem(),
          walletChange.getGold(),
          walletChange.getLevel(),
          walletChange.getXp(),
          currentActiveAvatarIds,
          currentLevel,
          false,
          endVipTime
      );
    }
    if (walletChange.getAvatarIds().size() != 0) {

      addAvatarPurchase(
          uuid,
          packageName,
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
          packageName,
          env,
          marketName
      );
    }

    if (walletChange.isHamiAded()) {
      userHamiFeginService.addHami(
          uuid,
          username,
          "BY_FOLLOW_SOCIAL :" + nameOfSocialNetwork,
          null,
          HamiAndNotificationType.FOLLOW_SOCIAL,
          packageName,
          env,
          marketName);
    }
    /////wallet changing ////////////////
    return walletChange;
  }

  @Override
  public AdvertisementSeeInfo addSeeThirdPartyAdvertisement(
      String username,
      String uuid,
      String packageName,
      String env,
      String marketName,
      String thirdPartPackageName,
      List<Integer> currentActiveAvatarIds,
      int currentLevel,
      long endVipTime) {
    List<Integer> avatars = new ArrayList<>();

    PackageThirdPartyInfo packageThirdPartyInfo = advertisementFeignService
        .getBy(
            packageName,
            env,
            marketName,
            thirdPartPackageName
        );

    long nowTime = timeService.getNowUnixFromInstantClass();
    Long timeSeeThirdPartyAdvertisement = userRepository.getTimeSeeThirdPartyAdvertisement(
        uuid,
        packageName,
        env,
        marketName,
        thirdPartPackageName
    );
    if (nowTime - timeSeeThirdPartyAdvertisement < packageThirdPartyInfo
        .getSecondsPassedBetweenWatching()) {

      long seconds =
          timeSeeThirdPartyAdvertisement + packageThirdPartyInfo.getSecondsPassedBetweenWatching()
              - nowTime;

      throw new UserSeeAdvertisementException(seconds);
    }

    userRepository.addSeeThirdPartyAdvertisement(
        uuid,
        packageName,
        env,
        marketName,
        thirdPartPackageName,
        nowTime

    );
    WalletChange walletChange = packageThirdPartyInfo.getWalletChange();
    /////wallet changing ////////////////
    if (walletChange.getGem() != 0 || walletChange.getXp() != 0
        || walletChange.getGold() != 0) {
      walletChange(apiKeyWalletChange,
          "",
          uuid,
          packageName,
          env,
          marketName,
          walletChange.getGem(),
          walletChange.getGold(),
          (short) walletChange.getLevel(),
          walletChange.getXp(),
          currentActiveAvatarIds,
          currentLevel,
          false,
          endVipTime
      );
    }
    if (walletChange.getAvatarIds() != null) {
//      if (walletChange.getAvatarIds().length != 0) {

//        for (int avatarId : walletChange.getAvatarIds()) {
//          avatars.add(avatarId);
//
//        }
      if (walletChange.getAvatarIds().size() != 0) {

        addAvatarPurchase(
            uuid,
            packageName,
            env,
            marketName,
            avatars
        );

//      }
      }
    }
    if (walletChange.getAddedVipPeriodTime() != 0) {
      userHamiFeginService.addVipUser(
          uuid,
          timeService.getNowUnixFromInstantClass(),
          walletChange.getAddedVipPeriodTime() + timeService.getNowUnixFromInstantClass(),
          walletChange.getAddedVipPeriodTime(),
          packageName,
          env,
          marketName
      );
    }

    if (walletChange.isHamiAded()) {
      userHamiFeginService.addHami(
          uuid,
          username,
          "BY_THIRD_PART_ADVERTISEMENT",
          null,
          HamiAndNotificationType.ADVERTISEMENT,
          packageName,
          env,
          marketName);
    }
    /////wallet changing ////////////////

    return new AdvertisementSeeInfo(
        thirdPartPackageName,
        new WalletChange(
            walletChange.getGem(),
            walletChange.getGold(),
            (short) walletChange.getLevel(),
            walletChange.getXp(),
            walletChange.isHamiAded(),
            avatars,
            walletChange.getAddedVipPeriodTime()
        )
    );


  }

  //////////////////////////////////////////
  ///////////private methodes///////////////
  /////////////////////////////////////////

  private UpdateStatus checkUpdate(int clientVersion, GameInfo gameInfo) {

    if (gameInfo == null) {
      return UpdateStatus.OK;

    }

    if (clientVersion < gameInfo.getSupportedVersion()) {
      return UpdateStatus.FORCE_UPDATE;
    }

    if (clientVersion < gameInfo.getLastVersion()) {
      return UpdateStatus.UPDATE;
    }

    return UpdateStatus.OK;
  }

  private String fix(String packageName) {
    return packageName.replace(".", "_");
  }

}