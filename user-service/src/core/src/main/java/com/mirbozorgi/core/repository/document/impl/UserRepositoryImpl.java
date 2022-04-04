package com.mirbozorgi.core.repository.document.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.UserRepository;
import com.mongodb.client.result.UpdateResult;
import com.mirbozorgi.core.domain.NewsData;
import com.mirbozorgi.core.domain.NotificationData;
import com.mirbozorgi.core.domain.Statistic;
import com.mirbozorgi.core.domain.UserGameProfile;
import com.mirbozorgi.core.domain.Wallet;
import com.mirbozorgi.core.document.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {


  @Autowired
  private CustomMongoRepository repository;

  @Override
  public User save(User user) {
    return repository.add(user);
  }

  @Override
  public void updateMarketForFirstAppOpen(
      String uuid,
      String packageName,
      String env,
      String market,
      String marketForFirstAppOpen) {
    Update update = new Update();
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);
    update.set(keyForGame + ".marketForFirstAppOpen", marketForFirstAppOpen);
    repository.update(User.class, update, where("uuid").is(uuid)
        .and(keyForGame).exists(true)
        .orOperator(
            Criteria.where(keyForGame + ".marketForFirstAppOpen").is(null),
            Criteria.where(keyForGame + ".marketForFirstAppOpen").is("")));
  }

  @Override
  public void delete(User user) {
    repository.delete(user);
  }

  @Override
  public User findById(String id) {
    return repository.fetchFirst(User.class, new ObjectId(id));
  }

  @Override
  public User findByUuId(String uuid) {
    return repository.fetchFirst(
        User.class,
        new String[]{
            "uuid",
            "gameProfiles"
        },
        where("uuid").is(uuid)
    );
  }


  @Override
  public List<User> findAll() {
    return repository.fetch(User.class, new String[]{
        "uuid",
        "gameProfiles"
    });
  }

  @Override
  public String getUserName(
      String uuid,
      String packageName,
      String env,
      String market
  ) {
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);

    ArrayList<CriteriaDefinition> where = new ArrayList<>();

    if (uuid == null) {
      uuid = "";
    }

    if (!uuid.isEmpty()) {
      where.add(where("uuid").is(uuid));
    }

    User user = repository.fetchFirst(
        User.class,
        new String[]{
            keyForGame + ".username"
        },
        where.toArray(new CriteriaDefinition[where.size()])
    );

    if (user == null) {
      return "";
    }
    return user
        .getGameProfiles().get(packageName)
        .get(env)
        .get(market)
        .getUsername();

  }

  @Override
  public void walletChange(
      String uuid,
      String packageName,
      String env,
      String market,
      int gem,
      int gold,
      Short level,
      Integer xp) {
    packageName = fix(packageName);
    Update update = new Update();
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);
    if (gem != 0) {
      update.inc(keyForGame + ".wallet.gem", gem);
    }
    if (gold != 0) {
      update.inc(keyForGame + ".wallet.gold", gold);
    }
    if (level != null) {
      if (!level.equals(Short.valueOf("0"))) {
        update.inc(keyForGame + ".wallet.level", level);
      }
    }
    if (xp != null) {
      if (xp != 0) {
        update.inc(keyForGame + ".wallet.xp", xp);
      }
    }

    if (update.getUpdateObject().isEmpty()) {
      return;
    }
    repository.update(User.class, update, where("uuid").is(uuid)
        .and(keyForGame).exists(true));


  }

  @Override
  public Wallet getWallet(
      String uuid,
      String packageName,
      String env,
      String market) {
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);
    ArrayList<CriteriaDefinition> where = new ArrayList<>();
    where.add(where("uuid").is(uuid));

    User user = repository.fetchFirst(
        User.class,
        new String[]{
            keyForGame + ".wallet"
        },
        where.toArray(new CriteriaDefinition[0])
    );

    if (user == null) {
      return null;
    }
    return user
        .getGameProfiles().get(packageName)
        .get(env)
        .get(market)
        .getWallet();

  }

  @Override
  public void setUserName(
      String uuid,
      String packageName,
      String env,
      String market,
      String userName) {
    Update update = new Update();
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);
    update.set(keyForGame + ".username", userName);
    repository.update(User.class, update, where("uuid").is(uuid)
        .and(keyForGame).exists(true));


  }


  @Override
  public List<User> findAllByXp(
      int maxXp,
      int minXp,
      String packageName,
      String env,
      String market,
      String uuid) {
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);

    return repository.fetch(
        User.class,
        new String[]{
            "uuid",
            keyForGame
        },
        new Sort(Sort.Direction.DESC, "_id"),
        100,
        Criteria.where(keyForGame + ".wallet.xp").lte(maxXp).gte(minXp)
            .and("uuid").ne(uuid)
            .and(keyForGame).exists(true));

//    new Sort(new Order(Direction.ASC, FIELD_NAME).ignoreCase()

  }


  @Override
  public UserGameProfile addGame(
      String uuid,
      String packageName,
      String env,
      String market,
      UserGameProfile userGameProfile) {

    packageName = fix(packageName);

    List players = repository.fetch(
        User.class,
        new String[]{"uuid"},
        where("uuid").is(uuid)
    );

    if (players.isEmpty()) {

      Map<String, Map<String, Map<String, UserGameProfile>>> games = new HashMap<>();
      Map<String, Map<String, UserGameProfile>> gamesWithEnv = new HashMap<>();
      Map<String, UserGameProfile> gameProfile = new HashMap<>();

      gameProfile.put(market, userGameProfile);
      gamesWithEnv.put(env, gameProfile);
      games.put(packageName, gamesWithEnv);

      User user = new User(
          uuid,
          games
      );

      repository.add(user);

    } else {

      String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);

      Update update = new Update();
      update.set(keyForGame, userGameProfile);

      repository.update(User.class, update, where("uuid").is(uuid));
    }

    return findByUuId(uuid)
        .getGameProfiles()
        .get(packageName).get(env).get(market);

  }

  @Override
  public void addAvatar(String uuid,
      String packageName,
      String env,
      String market,
      Integer avatarId) {
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);

    Update update = new Update();
    update.push(keyForGame + ".wallet.purchasedAvatars", avatarId);
    repository.update(User.class, update, where("uuid").is(uuid));
  }


  @Override
  public void setAvatar(
      String uuid,
      String packageName,
      String env,
      String market,
      List<Integer> avatarIds) {
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);
    Update update = new Update();
    update.set(keyForGame + ".wallet.activeAvatarIds", avatarIds);
    repository.update(User.class, update, where("uuid").is(uuid));
  }

  @Override
  public void updateClientVersion(
      String uuid,
      int clientVersion,
      String env,
      String packageName,
      String market) {
    packageName = fix(packageName);
    Update update = new Update();
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);
    update.set(keyForGame + ".clientVersion", clientVersion);
    repository.update(User.class, update, where("uuid").is(uuid));

  }

  @Override
  public int updateLevel(
      String uuid,
      Short level,
      String env,
      String packageName,
      String market) {
    packageName = fix(packageName);
    Update update = new Update();
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);
    update.set(keyForGame + ".wallet.level", level);
    UpdateResult result = repository.update(User.class, update, where("uuid").is(uuid));
    if (result.getModifiedCount() > 0) {
      return (int) level;
    }
    return 0;
  }

  @Override
  public void updateUnreadMassage(
      String uuid,
      int unreadMassage,
      String env,
      String packageName,
      String market) {
    packageName = fix(packageName);
    Update update = new Update();
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);
    update.inc(keyForGame + ".unreadMassage", unreadMassage);

    repository.update(User.class, update, where("uuid").is(uuid));
  }

  @Override
  public void resetUnreadMassage(String uuid, String env, String packageName,
      String market) {
    packageName = fix(packageName);
    Update update = new Update();
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);
    update.set(keyForGame + ".unreadMassage", 0);
    repository.update(User.class, update, where("uuid").is(uuid));

  }


  @Override
  public NotificationData addNotificationToAllUser(
      String gamePackageName,
      String env,
      String marketName,
      int minClientVersion,
      int maxClientVersion,
      NotificationData notificationData) {
    gamePackageName = fix(gamePackageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", gamePackageName, env, marketName);

    Update update = new Update()
        .set(keyForGame + ".notifications." + notificationData.getNotificationId(),
            notificationData);
    repository.updateAll(
        User.class,
        update,
        new Criteria().andOperator(
            where(keyForGame).exists(true),
            where(keyForGame + ".clientVersion").gte(minClientVersion),
            where(keyForGame + ".clientVersion").lte(maxClientVersion))
    );

    return notificationData;
  }

  @Override
  public List<User> getAllUuIdForFCM(
      String gamePackageName,
      String env,
      String marketName,
      int minClientVersion,
      int maxClientVersion) {

    gamePackageName = fix(gamePackageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", gamePackageName, env, marketName);

    return repository.fetch(
        User.class,
        new String[]{"uuid",
            keyForGame + ".tokenFCM"},
        new Criteria().andOperator(
            where(keyForGame).exists(true),
            where(keyForGame + ".clientVersion").gte(minClientVersion),
            where(keyForGame + ".clientVersion").lte(maxClientVersion))
    );
  }

  @Override
  public NotificationData addNotificationToOneUser(
      String gamePackageName,
      String env,
      String marketName,
      NotificationData notificationData,
      String uuid) {
    gamePackageName = fix(gamePackageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", gamePackageName, env, marketName);

    Update update = new Update()
        .set(keyForGame + ".notifications." + notificationData.getNotificationId(),
            notificationData);
    repository.updateAll(
        User.class,
        update,
        new Criteria().andOperator(
            where(keyForGame).exists(true),
            where("uuid").is(uuid))
    );

    return notificationData;
  }

  @Override
  public void popNotification(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      String notificationId) {
    gamePackageName = fix(gamePackageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", gamePackageName, env, marketName);

    repository.update(
        User.class,
        new Update().unset(keyForGame + ".notifications." + notificationId),
        where("uuid").is(uuid)
            .and(keyForGame + ".notifications." + notificationId).exists(true)
    );


  }

  @Override
  public void readNews(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      String notificationId) {
    gamePackageName = fix(gamePackageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", gamePackageName, env, marketName);

    repository.update(
        User.class,
        new Update().set(keyForGame + ".news." + notificationId + ".read", true),
        where("uuid").is(uuid)
            .and(keyForGame + ".news." + notificationId).exists(true)
    );
  }

  @Override
  public NotificationData findNotification(
      String uuid,
      String packageName,
      String env,
      String market,
      String notificationId) {
    packageName = fix(packageName);
    NotificationData notificationData = null;
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);
    try {
      notificationData = repository.fetchFirst(
          User.class,
          new String[]{
              "uuid",
              keyForGame + ".notifications." + notificationId
          },
          where("uuid").is(uuid)
              .and(keyForGame + ".notifications." + notificationId).exists(true))
          .getGameProfiles()
          .get(packageName)
          .get(env)
          .get(market)
          .getNotifications()
          .get(notificationId);

    } catch (Exception e) {
      return null;
    }

    return notificationData;
  }

  @Override
  public UserGameProfile getUserGameProfileForFriendShip(
      String uuid,
      String packageName,
      String env,
      String marketName) {
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, marketName);
    return repository.fetchFirst(
        User.class,
        new String[]{
            keyForGame + ".username",
            "uuid",
            keyForGame + ".wallet",
            keyForGame + ".statistic"
        },
        where("uuid").is(uuid)
    ).getGameProfiles().get(packageName).get(env).get(marketName);

  }


  @Override
  public NewsData addNews(
      String uuid,
      String packageName,
      String env,
      String marketName,
      NewsData newsData) {
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, marketName);

    Update update = new Update()
        .set(keyForGame + ".news." + newsData.getNotificationId(),
            newsData);
    repository.update(
        User.class,
        update,
        new Criteria().andOperator(
            where(keyForGame).exists(true),
            where("uuid").is(uuid)));

    return newsData;
  }

  @Override
  public Boolean useDailyReward(
      LocalDate now,
      String uuid,
      String packageName,
      String env,
      String marketName) {
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, marketName);

    Update update = new Update()
        .set(keyForGame + ".lastDailyRewardClaimDate", now);
    UpdateResult updateResult = repository.update(
        User.class,
        update,
        new Criteria().andOperator(
            where(keyForGame).exists(true),
            where("uuid").is(uuid))

    );
    return updateResult.getModifiedCount() > 0;
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

    packageName = fix(packageName);
    Update update = new Update();
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);

    if (incGameNumber != 0) {
      update.inc(keyForGame + ".statistic.gamesParticipated", incGameNumber);
    }
    if (incGemSpent != 0) {
      update.inc(keyForGame + ".statistic.gemSpent", incGemSpent);
    }
    if (inchamiCount != 0) {
      update.inc(keyForGame + ".statistic.hamiCount", inchamiCount);
    }
    if (incInvitation != 0) {
      update.inc(keyForGame + ".statistic.invitations", incInvitation);
    }

    if (incWin != 0) {
      update.inc(keyForGame + ".statistic.win", incWin);

    }
    if (incLose != 0) {
      update.inc(keyForGame + ".statistic.lose", incLose);
    }

    repository.update(User.class, update, where("uuid").is(uuid)
        .and(keyForGame).exists(true));


  }

  @Override
  public void updateFirstWeeklyXp(
      String uuid,
      String packageName,
      String env,
      String market,
      int quantity) {
    packageName = fix(packageName);
    Update update = new Update();
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);

    update.inc(keyForGame + ".statistic.firstPlaceOfWeeklyXp", quantity);

    repository.update(User.class, update, where("uuid").is(uuid)
        .and(keyForGame).exists(true));


  }

  @Override
  public void updateYearPassedRegister(
      String uuid,
      String packageName,
      String env,
      String market,
      int quantity
  ) {
    packageName = fix(packageName);
    Update update = new Update();
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, market);

    update.set(keyForGame + ".statistic.yearPassedFromRegister", quantity);

    repository.update(User.class, update, where("uuid").is(uuid)
        .and(keyForGame).exists(true));
  }

  @Override
  public Statistic getUserStatistic(
      String uuid,
      String packageName,
      String env,
      String marketName) {

    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, marketName);
    return repository.fetchFirst(
        User.class,
        new String[]{
            keyForGame + ".username",
            keyForGame + ".statistic"
        },
        where("uuid").is(uuid)
    ).getGameProfiles().get(packageName).get(env).get(marketName).getStatistic();

  }

  @Override
  public UserGameProfile getUserWalletAndStatistic(
      String uuid,
      String packageName,
      String env,
      String marketName) {

    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, marketName);
    UserGameProfile userGameProfile = repository.fetchFirst(
        User.class,
        new String[]{
            keyForGame + ".wallet",
            keyForGame + ".statistic"
        },
        where("uuid").is(uuid)
    ).getGameProfiles().get(packageName).get(env).get(marketName);

    return userGameProfile;
  }

  @Override
  public void addLastLogin(
      String uuid,
      String packageName,
      String env,
      String marketName,
      LocalDate date) {

    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, marketName);

    Update update = new Update()
        .push(keyForGame + ".lastLogin", date);
    repository.update(
        User.class,
        update,
        new Criteria().andOperator(
            where(keyForGame).exists(true),
            where("uuid").is(uuid)));


  }

  @Override
  public void resetLastLogin(
      String uuid,
      String packageName,
      String env,
      String marketName) {
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, marketName);

    Update update = new Update()
        .set(keyForGame + ".lastLogin", new ArrayList<>());
    repository.update(
        User.class,
        update,
        new Criteria().andOperator(
            where(keyForGame).exists(true),
            where("uuid").is(uuid)));
  }

  @Override
  public void addFollowSocialNetworks(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String nameOfSocialNetwork) {
    packageName = fix(packageName);
    Update update = new Update();
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, marketName);

    update.set(keyForGame + ".statistic.isFollowNetworks." + nameOfSocialNetwork, true);
    if (update.getUpdateObject().isEmpty()) {
      return;
    }
    repository.update(User.class, update, where("uuid").is(uuid)
        .and(keyForGame).exists(true));
  }

  @Override
  public Map<String, Boolean> getAllSocialNetworkFollowedByUser(
      String uuid,
      String packageName,
      String env,
      String marketName) {
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, marketName);
    Statistic statistic = repository.fetchFirst(
        User.class,
        new String[]{
            keyForGame + ".statistic"
        },
        where("uuid").is(uuid))
        .getGameProfiles()
        .get(packageName)
        .get(env)
        .get(marketName)
        .getStatistic();
    if (statistic.getIsFollowNetworks() == null) {
      return null;
    }
    return statistic.getIsFollowNetworks();


  }

  @Override
  public void addSeeThirdPartyAdvertisement(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String packageNameOfThirdParty,
      long nowTime
  ) {
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, marketName);

    Update update = new Update();
    update.set(
        keyForGame + ".lastSeenOfThirdPartyAdvertisements."
            + packageNameOfThirdParty, nowTime);
    if (update.getUpdateObject().isEmpty()) {
      return;
    }
    repository.update(User.class, update, where("uuid").is(uuid)
        .and(keyForGame).exists(true));

  }

  @Override
  public void updateLevel(
      String uuid,
      String packageName,
      String env,
      String marketName,
      Short level) {
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, marketName);

    Update update = new Update();
    update.set(
        keyForGame + ".wallet.level", level);
    repository.update(User.class, update, where("uuid").is(uuid)
        .and(keyForGame).exists(true));
  }


  @Override
  public Long getTimeSeeThirdPartyAdvertisement(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String packageNameOfThirdParty) {
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, marketName);
    Map<String, Long> lastSeenOfThirdPartyAdvertisements = repository.fetchFirst(
        User.class,
        new String[]{
            keyForGame + ".lastSeenOfThirdPartyAdvertisements"
        },
        where("uuid").is(uuid))
        .getGameProfiles()
        .get(packageName)
        .get(env)
        .get(marketName)
        .getLastSeenOfThirdPartyAdvertisements();
    if (lastSeenOfThirdPartyAdvertisements.get(packageNameOfThirdParty) == null) {
      return 0L;
    }
    return lastSeenOfThirdPartyAdvertisements
        .get(packageNameOfThirdParty);
  }

  @Override
  public void updateTokenFCM(
      String uuid,
      String packageName,
      String env,
      String marketName,
      String tokenFCM) {
    packageName = fix(packageName);
    String keyForGame = String.format("gameProfiles.%s.%s.%s", packageName, env, marketName);

    Update update = new Update();
    update.set(
        keyForGame + ".tokenFCM", tokenFCM);
    repository.update(User.class, update, where("uuid").is(uuid)
        .and(keyForGame).exists(true));
  }


  private String fix(String packageName) {
    return packageName.replace(".", "_");
  }

}
