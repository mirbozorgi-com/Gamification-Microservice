package mirbozorgi.base.feignService;

import java.util.ArrayList;
import java.util.List;
import mirbozorgi.base.domain.game.WalletChangeModel;
import mirbozorgi.base.domain.notification.AddNotificationToAllUser;
import mirbozorgi.base.domain.notification.AddNotificationToOneUserModel;
import mirbozorgi.base.domain.user.CurrentLevelInfo;
import mirbozorgi.base.domain.user.GetGameProfileForFriendShip;
import mirbozorgi.base.domain.user.GetUserNameData;
import mirbozorgi.base.domain.user.UserGetGlobalResponse;
import mirbozorgi.base.domain.user.WalletChange;
import mirbozorgi.base.domain.user.WalletWithVipTime;

public interface UserFeignService {

  UserGetGlobalResponse getByUuId(
      String uuid,
      String gamePackageName,
      String env,
      String marketName);


  Object addNotificationToAllUser(
      AddNotificationToAllUser model,
      String gamePackageName,
      String env,
      String marketName);

  Object addNotificationToOneUser(
      AddNotificationToOneUserModel model,
      String gamePackageName,
      String env,
      String marketName);

  GetGameProfileForFriendShip getFriendProfile(
      String uuid,
      String packageName,
      String env,
      String marketName
  );


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

  ArrayList findAllByXp(
      int maxXp,
      int minXp,
      String packageName,
      String env,
      String market,
      String notUuIdIdInclude);


  GetUserNameData getUserName(
      String uuid,
      String packageName,
      String env,
      String market
  );

  WalletChange walletChange(
      String apiKey,
      WalletChangeModel model,
      String gamePackageName,
      String uuid,
      String env,
      String marketName);

  WalletWithVipTime getWallet(
      String gamePackageName,
      String uuid,
      String env,
      String marketName);

  void addAvatarPurchase(
      String uuid,
      String packageName,
      String env,
      String marketName,
      List<Integer> avatarIds);

  void updateUserStatistic(
      String uuid,
      String packageName,
      String env,
      String market,
      int incHamiCount,
      int incGameNumber,
      int incInvitation,
      int incGemSpent,
      int incWin,
      int incLose
  );

  void incXpWeeklyFirstPlace(
      String gamePackageName,
      String uuid,
      String env,
      String marketName);


  CurrentLevelInfo updateLevel(
      String gamePackageName,
      String uuid,
      String env,
      String marketName);
}
