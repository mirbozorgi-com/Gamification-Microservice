package mirbozorgi.base.feigns;


import mirbozorgi.base.domain.game.WalletChangeModel;
import mirbozorgi.base.domain.notification.AddNotificationToAllUser;
import mirbozorgi.base.domain.notification.AddNotificationToOneUserModel;
import mirbozorgi.base.domain.user.AddAvatarModel;
import mirbozorgi.base.domain.user.UpdateUserStatisticModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient("user-service")
public interface UserFeign {


  @RequestMapping(method = RequestMethod.GET, path = "user/get-uu-id")
  Object getByUuId(
      @RequestHeader String uuid,
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader String marketName);

  @RequestMapping(method = RequestMethod.POST, path = "user/notification/add-all-user")
  Object addNotificationToAllUser(@Validated @RequestBody AddNotificationToAllUser model,
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader String marketName);


  @RequestMapping(method = RequestMethod.GET, path = "/user/get-profile")
  Object getFriendProfile(
      @RequestHeader String uuid,
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader String marketName
  );

  @RequestMapping(method = RequestMethod.GET, path = "/user/update-unread-massage")
  void updateUnreadMassage(
      @RequestParam String uuid,
      @RequestParam int unreadMassage,
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader String marketName);

  @RequestMapping(method = RequestMethod.GET, path = "/user/reset-unread-massage")
  void resetUnreadMassage(
      @RequestHeader String uuid,
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader String marketName);


  @RequestMapping(value = "user/get-all-by-xp", method = RequestMethod.GET)
  Object getAllByXp(
      @RequestParam Integer maxXp,
      @RequestParam Integer minXp,
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader String marketName,
      @RequestHeader String uuid);

  @RequestMapping(value = "user/get-useraname", method = RequestMethod.GET)
  Object getUsername(
      @RequestHeader String uuid,
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader String marketName
  );

  @RequestMapping(method = RequestMethod.POST, path = "wallet/change")
  Object walletChange(
      @RequestBody @Validated WalletChangeModel model,
      @RequestHeader String gamePackageName,
      @RequestHeader String uuid,
      @RequestHeader String env,
      @RequestHeader String marketName,
      @RequestHeader String apiKey);

  @RequestMapping(method = RequestMethod.GET, path = "wallet/get")
  Object walletGet(
      @RequestHeader String gamePackageName,
      @RequestHeader String uuid,
      @RequestHeader String env,
      @RequestHeader String marketName);


  @RequestMapping(value = "wallet/add-avatar", method = RequestMethod.POST)
  Object addAvatar(
      @Validated @RequestBody AddAvatarModel model,
      @RequestHeader String gamePackageName,
      @RequestHeader String uuid,
      @RequestHeader String env,
      @RequestHeader String marketName);


  @RequestMapping(value = "statistic/update-user-statistic", method = RequestMethod.POST)
  Object statistic(
      @Validated @RequestBody UpdateUserStatisticModel model,
      @RequestHeader String gamePackageName,
      @RequestHeader String uuid,
      @RequestHeader String env,
      @RequestHeader String marketName);


  @RequestMapping(value = "statistic/inc-the-most-xp-weekly", method = RequestMethod.POST)
  Object incXpWeeklyFirstPlace(
      @RequestHeader String gamePackageName,
      @RequestHeader String uuid,
      @RequestHeader String env,
      @RequestHeader String marketName);

  @RequestMapping(value = "user/update-level", method = RequestMethod.POST)
  Object updateLevel(
      @RequestHeader String gamePackageName,
      @RequestHeader String uuid,
      @RequestHeader String env,
      @RequestHeader String marketName
  );

  @RequestMapping(method = RequestMethod.POST, path = "user/notification/add-one-user")
  Object addNotificationOneUser(@Validated @RequestBody AddNotificationToOneUserModel model,
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader String marketName);

}
