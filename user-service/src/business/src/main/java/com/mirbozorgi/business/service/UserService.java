package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.friendship.GetGameProfileForFriendShip;
import com.mirbozorgi.business.domain.game.AddGameToUserData;
import com.mirbozorgi.business.domain.user.AdvertisementSeeInfo;
import com.mirbozorgi.business.domain.user.UserGetGlobalResponse;
import com.mirbozorgi.business.domain.user.UserGetResponse;
import com.mirbozorgi.business.domain.user.UserGetWalletAndStatistic;
import com.mirbozorgi.business.domain.user.UserStatistic;
import com.mirbozorgi.business.domain.TimeAndVersioningInfo;
import com.mirbozorgi.business.domain.WalletWithVipTime;
import com.mirbozorgi.core.domain.NotificationData;
import java.util.List;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;
import mirbozorgi.base.domain.user.CurrentLevelInfo;
import mirbozorgi.base.domain.user.GetUserNameData;
import mirbozorgi.base.domain.user.UserByXpGetResponse;
import mirbozorgi.base.domain.user.WalletChange;

public interface UserService {

  void delete(String id);

  UserGetResponse findById(String id);

  UserGetGlobalResponse findByUuId(
      String uuid,
      String gamePackageName,
      String env,
      String marketName);

  List<UserGetResponse> findAll();

  List<UserByXpGetResponse> findAllByXp(
      int maxXp,
      int minXp,
      String packageName,
      String env,
      String market,
      String uuid);

  GetUserNameData getUserName(
      String uuid,
      String packageName,
      String env,
      String market
  );


  WalletChange walletChange(
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
      long endVipTime);

  WalletWithVipTime getWallet(String uuid,
      String packageName,
      String env,
      String market);


  WalletChange walletOfflineChange(
      String uuid,
      String packageName,
      String env,
      String market,
      int gem,
      int gold,
      int xp);


  CurrentLevelInfo updateLevel(
      String uuid,
      String env,
      String packageName,
      String market);

  void setUserName(
      String username,
      String uuid,
      String packageName,
      String env,
      String market
  );

  AddGameToUserData addOrUpdateGame(
      String uuid,
      String packageName,
      String env,
      String market,
      String cohortName,
      //profile data :
      boolean test,
      boolean debug,
      int clientVersion,
      String keyForClaimDailyContinueReward);

  String setTokenFCM(String uuid,
      String packageName,
      String env,
      String market,
      String tokenFCM);


  TimeAndVersioningInfo getTimeAndVersion(
      String packageName,
      String env,
      int clientVersion);


  void updateUnreadMassage(
      String uuid,
      int unreadMassage,
      String env,
      String packageName,
      String market);


  void resetUnreadMassage(
      String uuid,
      String env,
      String packageName,
      String market);


  NotificationData addNotificationToAllUser(
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
      Boolean isFcmSend);


  NotificationData addNotificationToOneUser(
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
      HamiAndNotificationType type);

  Object popNotification(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      String notificationId);


  GetGameProfileForFriendShip getGameProfileForFriendShip(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String uuidParam
  );


  void addNews(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String name,
      Object config);

  void readNews(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String id);


  void addAvatarPurchase(
      String uuid,
      String packageName,
      String env,
      String marketName,
      List<Integer> avatarIds);

  void setAvatarId(
      String uuid,
      String packageName,
      String env,
      String marketName,
      List<Integer> avatarIds);


  WalletChange useDailyReward(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String username);

  void updateUserStatistic(
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
  );

  void updateFirstWeeklyXp(
      String uuid,
      String packageName,
      String env,
      String market,
      int quantity
  );

  UserStatistic getUserStatistic(
      String uuid,
      String packageName,
      String env,
      String market
  );

  UserGetWalletAndStatistic getWalletAndStatistic(
      String uuid,
      String packageName,
      String env,
      String market
  );

  WalletChange addSocialNetworkFollowToProfile(
      String uuid,
      String username,
      String packageName,
      String env,
      String marketName,
      String nameOfSocialNetwork,
      List<Integer> currentActiveAvatarIds,
      int currentLevel,
      long endVipTime);

  AdvertisementSeeInfo addSeeThirdPartyAdvertisement(
      String username,
      String uuid,
      String packageName,
      String env,
      String marketName,
      String thirdPartPackageName,
      List<Integer> currentActiveAvatarIds,
      int currentLevel,
      long endVipTime);


}
