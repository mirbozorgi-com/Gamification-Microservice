package mirbozorgi.base.feignService.impl;

import java.util.ArrayList;
import java.util.List;
import mirbozorgi.base.domain.game.WalletChangeModel;
import mirbozorgi.base.domain.notification.AddNotificationToAllUser;
import mirbozorgi.base.domain.notification.AddNotificationToOneUserModel;
import mirbozorgi.base.domain.user.AddAvatarModel;
import mirbozorgi.base.domain.user.CurrentLevelInfo;
import mirbozorgi.base.domain.user.GetGameProfileForFriendShip;
import mirbozorgi.base.domain.user.GetUserNameData;
import mirbozorgi.base.domain.user.UpdateUserStatisticModel;
import mirbozorgi.base.domain.user.UserGetGlobalResponse;
import mirbozorgi.base.domain.user.Wallet;
import mirbozorgi.base.domain.user.WalletChange;
import mirbozorgi.base.domain.user.WalletWithVipTime;
import mirbozorgi.base.exception.GlobalExceptionService;
import mirbozorgi.base.feignService.FeignErrorFeignService;
import mirbozorgi.base.feignService.SerializerFeignService;
import mirbozorgi.base.feignService.UserFeignService;
import mirbozorgi.base.feigns.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFeignServiceImpl implements UserFeignService {


  @Autowired
  private UserFeign userFeign;

  @Autowired
  private FeignErrorFeignService feignErrorFeign;

  @Autowired
  private SerializerFeignService serializerFeignService;


  @Override
  public UserGetGlobalResponse getByUuId(
      String uuid,
      String gamePackageName,
      String env,
      String marketName) {
    UserGetGlobalResponse userGetGlobalResponse = null;
    try {

      Object o = userFeign.getByUuId(
          uuid,
          gamePackageName,
          env,
          marketName
      );

      userGetGlobalResponse = serializerFeignService
          .toObjectFromFeign(o, UserGetGlobalResponse.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getByUuId", "user-service", ex, feignErrorFeign
      );

    }

    return userGetGlobalResponse;
  }

  @Override
  public Object addNotificationToAllUser(
      AddNotificationToAllUser model,
      String gamePackageName,
      String env,
      String marketName) {
    Object o = null;
    try {

      o = userFeign.addNotificationToAllUser(
          model,
          gamePackageName,
          env,
          marketName
      );

      o = serializerFeignService.toObjectFromFeign(o, Object.class);


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "addNotificationToAllUser", "user-service", ex, feignErrorFeign
      );

    }

    return o;
  }

  @Override
  public Object addNotificationToOneUser(
      AddNotificationToOneUserModel model,
      String gamePackageName,
      String env,
      String marketName) {
    Object o = null;
    try {

      o = userFeign.addNotificationOneUser(
          model,
          gamePackageName,
          env,
          marketName
      );

      o = serializerFeignService.toObjectFromFeign(o, Object.class);


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "addNotificationToOneUser", "user-service", ex, feignErrorFeign
      );

    }

    return o;
  }


  @Override
  public GetGameProfileForFriendShip getFriendProfile(
      String uuid,
      String packageName,
      String env,
      String marketName) {
    GetGameProfileForFriendShip gameProfileForFriendShip = null;
    try {

      Object o = userFeign.getFriendProfile(
          uuid,
          packageName,
          env,
          marketName
      );

      gameProfileForFriendShip = serializerFeignService
          .toObjectFromFeign(o, GetGameProfileForFriendShip.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getFriendProfile", "user-service", ex, feignErrorFeign
      );


    }

    return gameProfileForFriendShip;
  }

  @Override
  public void updateUnreadMassage(
      String uuid,
      int unreadMassage,
      String env,
      String packageName,
      String market) {
    try {

      userFeign.updateUnreadMassage(
          uuid,
          unreadMassage,
          env,
          packageName,
          market
      );


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "updateUnreadMassage", "user-service", ex, feignErrorFeign
      );


    }

  }

  @Override
  public void resetUnreadMassage(
      String uuid,
      String env,
      String packageName,
      String market) {
    try {

      userFeign.resetUnreadMassage(
          uuid,
          env,
          packageName,
          market
      );


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "resetUnreadMassage", "user-service", ex, feignErrorFeign
      );


    }
  }

  @Override
  public ArrayList findAllByXp(
      int maxXp,
      int minXp,
      String packageName,
      String env,
      String market,
      String notUuIdIdInclude) {
    ArrayList arrayList = null;

    try {

      Object allByXp = userFeign
          .getAllByXp(maxXp, minXp, packageName, env, market, notUuIdIdInclude);

      arrayList = serializerFeignService
          .toObjectFromFeign(allByXp, ArrayList.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "findAllByXp", "user-service", ex, feignErrorFeign
      );


    }
    if (arrayList == null) {
      return new ArrayList();
    }
    return arrayList;
  }

  @Override
  public GetUserNameData getUserName(
      String uuid,
      String packageName,
      String env,
      String market
  ) {
    GetUserNameData getUserNameData = null;
    try {

      Object o = userFeign.getUsername(
          uuid,
          packageName,
          env,
          market
      );

      getUserNameData = serializerFeignService
          .toObjectFromFeign(o, GetUserNameData.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getUserName", "user-service", ex, feignErrorFeign
      );


    }

    return getUserNameData;
  }

  @Override
  public WalletChange walletChange(
      String apiKey,
      WalletChangeModel model,
      String gamePackageName,
      String uuid,
      String env,
      String marketName) {
    WalletChange wallet = null;
    try {

      Object o = userFeign.walletChange(
          model,
          gamePackageName,
          uuid,
          env,
          marketName,
          apiKey
      );

      wallet = serializerFeignService
          .toObjectFromFeign(o, WalletChange.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "walletChange", "user-service", ex, feignErrorFeign
      );


    }
    return wallet;

  }

  @Override
  public WalletWithVipTime getWallet(
      String gamePackageName,
      String uuid,
      String env,
      String marketName) {
    WalletWithVipTime wallet = null;
    try {

      Object o = userFeign.walletGet(
          gamePackageName,
          uuid,
          env,
          marketName
      );

      wallet = serializerFeignService
          .toObjectFromFeign(o, WalletWithVipTime.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getWallet", "user-service", ex, feignErrorFeign
      );


    }
    return wallet;
  }

  @Override
  public void addAvatarPurchase(
      String uuid,
      String packageName,
      String env,
      String marketName,
      List<Integer> avatarIds) {

    try {

      Object o = userFeign.addAvatar(
          new AddAvatarModel(avatarIds),
          packageName,
          uuid,
          env,
          marketName
      );

      serializerFeignService.toObjectFromFeign(o, Wallet.class);


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "addAvatar", "user-service", ex, feignErrorFeign
      );


    }


  }

  @Override
  public void updateUserStatistic(
      String uuid,
      String packageName,
      String env,
      String market,
      int incHamiCount,
      int incGameNumber,
      int incInvitation,
      int incGemSpent,
      int incWin,
      int incLose) {

    try {

      Object o = userFeign.statistic(
          new UpdateUserStatisticModel(
              incInvitation,
              incGameNumber,
              incHamiCount,
              incGemSpent,
              incWin,
              incLose
          ),
          packageName,
          uuid,
          env,
          market
      );

      serializerFeignService.toObjectFromFeign(o, Object.class);


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "statistic", "user-service", ex, feignErrorFeign
      );


    }

  }

  @Override
  public void incXpWeeklyFirstPlace(
      String gamePackageName,
      String uuid,
      String env,
      String marketName) {

    try {

      Object o = userFeign.incXpWeeklyFirstPlace(
          gamePackageName,
          uuid,
          env,
          marketName
      );

      serializerFeignService.toObjectFromFeign(o, Object.class);


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "incXpWeeklyFirstPlace", "user-service", ex, feignErrorFeign
      );


    }

  }

  @Override
  public CurrentLevelInfo updateLevel(
      String gamePackageName,
      String uuid,
      String env,
      String marketName) {
    CurrentLevelInfo currentLevelInfo = null;
    try {

      Object o = userFeign.updateLevel(
          gamePackageName,
          uuid,
          env,
          marketName
      );

      currentLevelInfo = serializerFeignService
          .toObjectFromFeign(o, CurrentLevelInfo.class);


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "updateLevel ", "user-service", ex, feignErrorFeign
      );


    }
    return currentLevelInfo;
  }

}
