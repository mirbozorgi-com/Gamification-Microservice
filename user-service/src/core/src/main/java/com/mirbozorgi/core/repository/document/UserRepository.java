package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.domain.NewsData;
import com.mirbozorgi.core.domain.NotificationData;
import com.mirbozorgi.core.domain.Statistic;
import com.mirbozorgi.core.domain.UserGameProfile;
import com.mirbozorgi.core.domain.Wallet;
import com.mirbozorgi.core.document.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface UserRepository {


  User save(User user);

  void updateMarketForFirstAppOpen(
      String uuid,
      String packageName,
      String env,
      String market,
      String marketForFirstAppOpen
  );

  void delete(User user);

  User findById(String id);

  User findByUuId(String uuid);

  List<User> findAll();

  String getUserName(
      String uuid,
      String packageName,
      String env,
      String market
  );

  void walletChange(
      String uuid,
      String packageName,
      String env,
      String market,
      int gem,
      int gold,
      Short level,
      Integer xp);

  Wallet getWallet(String uuid,
      String packageName,
      String env,
      String market);

  void setUserName(
      String uuid,
      String packageName,
      String env,
      String market,
      String userName);

  List<User> findAllByXp(
      int maxXp,
      int minXp,
      String packageName,
      String env,
      String market,
      String uuid);

  UserGameProfile addGame(
      String uuid,
      String packageName,
      String env,
      String market,
      UserGameProfile userGameProfile);


  void addAvatar(
      String uuid,
      String packageName,
      String env,
      String market,
      Integer avatarId);

  void setAvatar(
      String uuid,
      String packageName,
      String env,
      String market,
      List<Integer> avatarIds);

  void updateClientVersion(
      String uuid,
      int clientVersion,
      String env,
      String packageName,
      String market);


  int updateLevel(
      String uuid,
      Short level,
      String env,
      String packageName,
      String market);


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
      int minClientVersion,
      int maxClientVersion,
      NotificationData notificationData);


  List<User> getAllUuIdForFCM(
      String gamePackageName,
      String env,
      String marketName,
      int minClientVersion,
      int maxClientVersion
  );


  NotificationData addNotificationToOneUser(
      String gamePackageName,
      String env,
      String marketName,
      NotificationData notificationData,
      String uuid);

  void popNotification(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      String notificationId);

  void readNews(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      String notificationId);


  NotificationData findNotification(
      String uuid,
      String packageName,
      String env,
      String market,
      String notificationId);

  UserGameProfile getUserGameProfileForFriendShip(
      String uuid,
      String packageName,
      String env,
      String marketName
  );


  NewsData addNews(
      String uuid,
      String packageName,
      String env,
      String marketName,
      NewsData newsData);

  Boolean useDailyReward(
      LocalDate now,
      String uuid,
      String packageName,
      String env,
      String marketName
  );

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

  void updateYearPassedRegister(
      String uuid,
      String packageName,
      String env,
      String market,
      int quantity
  );

  Statistic getUserStatistic(
      String uuid,
      String packageName,
      String env,
      String marketName
  );

  UserGameProfile getUserWalletAndStatistic(
      String uuid,
      String packageName,
      String env,
      String marketName
  );

  void addLastLogin(
      String uuid,
      String packageName,
      String env,
      String marketName,
      LocalDate date);

  void resetLastLogin(
      String uuid,
      String packageName,
      String env,
      String marketName);

  void addFollowSocialNetworks(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String nameOfSocialNetwork);

  Map<String, Boolean> getAllSocialNetworkFollowedByUser(
      String uuid,
      String packageName,
      String env,
      String marketName);

  void addSeeThirdPartyAdvertisement(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String packageNameOfThirdParty,
      long nowTime
  );


  void updateLevel(
      String uuid,
      String packageName,
      String env,
      String marketName,
      Short level
  );

  Long getTimeSeeThirdPartyAdvertisement(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String packageNameOfThirdParty
  );


  void updateTokenFCM(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String tokenFCM
  );

}
