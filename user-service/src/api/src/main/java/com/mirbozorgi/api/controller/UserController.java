package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.AddNewsModel;
import com.mirbozorgi.api.model.AddNotificationToAllUserModel;
import com.mirbozorgi.api.model.AddNotificationToOneUserModel;
import com.mirbozorgi.api.model.AddOrUpdateGameModel;
import com.mirbozorgi.api.model.AddSeeAdvertisementModel;
import com.mirbozorgi.api.model.AddSocialNetFollowModel;
import com.mirbozorgi.api.model.AddTokenFCMModel;
import com.mirbozorgi.api.model.ClientVersionModel;
import com.mirbozorgi.api.model.PopNotificationModel;
import com.mirbozorgi.api.model.ReadNewsModel;
import com.mirbozorgi.api.model.SetUserNameModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.SerializerService;
import com.mirbozorgi.business.service.UserService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuId;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuIdDefaultMarket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private SerializerService serializerService;

  @Autowired
  private CurrentContextService currentContextService;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(@RequestParam String id) {
    return ResponseHelper.response(userService.findById(id));
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-uu-id", method = RequestMethod.GET)
  public ResponseEntity getByGlobal() {
    return ResponseHelper.response(userService.findByUuId(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket()
    ));
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-username", method = RequestMethod.GET)
  public ResponseEntity getUsername() {
    return ResponseHelper.response(userService.getUserName(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket()
    ));
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/set-username", method = RequestMethod.POST)
  public ResponseEntity setUserName(@RequestBody @Validated SetUserNameModel model) {
    userService.setUserName(
        model.getUserName(),
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket()
    );
    return ResponseHelper.response(true);

  }


  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll() {
    return ResponseHelper.response(userService.findAll());
  }
///////////////////////////get by xp renge /////////////////////////////////////

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-all-by-xp", method = RequestMethod.GET)
  public ResponseEntity getAllByXp(
      @RequestParam Integer maxXp,
      @RequestParam Integer minXp) {
    return ResponseHelper.response(userService.findAllByXp(
        maxXp,
        minXp,
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentUuId()
    ));
  }


  ////////////////////////////cohort and registering///////////////////////////////////////////////
  //we should get original market to get deviceId so we should  use
  // GamePropertiesWithUuId instead of GamePropertiesWithUuIdDefaultMarket
  @GamePropertiesWithUuId
  @RequestMapping(value = "/info", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody AddOrUpdateGameModel model) {
    return ResponseHelper.response(userService.addOrUpdateGame(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getCohortName(),
        model.isTest(),
        model.isDebug(),
        model.getClientVersion(),
        model.getKeyForClaimDailyContinueReward()));
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/token-fcm", method = RequestMethod.POST)
  public ResponseEntity setTokenFCM(@Validated @RequestBody AddTokenFCMModel model) {
    return ResponseHelper.response(userService.setTokenFCM(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getTokenFCM()
    ));
  }

  //////////////////////////versioning and time//////////////////////////////
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/time-and-versioning", method = RequestMethod.POST)
  public ResponseEntity timeAndVersioning(@RequestBody ClientVersionModel model) {
    return ResponseHelper.response(userService.getTimeAndVersion(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        model.getClientVersion()
    ));
  }


  ////////////////////////////chat///////////////////////////////////////////////////
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/update-unread-massage", method = RequestMethod.GET)
  public ResponseEntity updateUnreadMassage(
      @RequestParam int unreadMassage) {
    userService.updateUnreadMassage(
        currentContextService.getCurrentUuId(),
        unreadMassage,
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket()
    );

    return ResponseHelper.response(true);

  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/reset-unread-massage", method = RequestMethod.GET)
  public ResponseEntity resetUnreadMassage() {
    userService.resetUnreadMassage(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket()
    );

    return ResponseHelper.response(true);

  }

  ////////////////////////////notification///////////////////////////////////////////////////

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/notification/add-all-user", method = RequestMethod.POST)
  public ResponseEntity addNotificationToAllUser(
      @Validated @RequestBody AddNotificationToAllUserModel model) {
    return ResponseHelper.response(userService.addNotificationToAllUser(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getName(),
        model.getNotificationId(),
        StringUtils.isEmpty(model.getConfig())
            ? null
            : serializerService.toObj(model.getConfig(), Object.class),
        model.getMinClientVersion(),
        model.getMaxClientVersion(),
        model.getRemoveAble(),
        model.getGem(),
        model.getGold(),
        model.getLevel(),
        model.getXp(),
        model.isHamiAded(),
        model.getAvatarIds(),
        model.getAddedVipPeriodTime(),
        model.getTitle(),
        model.getMessage(),
        model.getTopic(),
        model.getType(),
        model.getFcmSend()

    ));
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/notification/add-one-user", method = RequestMethod.POST)
  public ResponseEntity addNotificationToOneUser(
      @Validated @RequestBody AddNotificationToOneUserModel model) {
    return ResponseHelper.response(userService.addNotificationToOneUser(
        model.getPlayerUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getName(),
        StringUtils.isEmpty(model.getConfig())
            ? null
            : serializerService.toObj(model.getConfig(), Object.class),
        model.getRemoveAble(),
        model.getGem(),
        model.getGold(),
        model.getLevel(),
        model.getXp(),
        model.isHamiAded(),
        model.getAvatarIds(),
        model.getAddedVipPeriodTime(),
        model.getTitle(),
        model.getMessage(),
        model.getTopic(),
        model.getType()

    ));
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/notification/pop", method = RequestMethod.POST)
  public ResponseEntity popNotification(
      @Validated @RequestBody PopNotificationModel model) {

    return ResponseHelper.response(userService.popNotification(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getId()
    ));

  }

  ////////////////////////////friend-ship///////////////////////////////////////////////////\
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-profile", method = RequestMethod.GET)
  public ResponseEntity getFriendProfile(@RequestParam(required = false) String uuid) {
    return ResponseHelper.response(userService.getGameProfileForFriendShip(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        uuid
    ));
  }

  /////////////////////////news///////////////////////////////////////////////
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/news/add", method = RequestMethod.POST)
  public ResponseEntity addNews(
      @Validated @RequestBody AddNewsModel model) {

    userService.addNews(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getName(),
        StringUtils.isEmpty(model.getConfig())
            ? null
            : serializerService.toObj(model.getConfig(), Object.class)
    );

    return ResponseHelper.response(true);
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/news/read", method = RequestMethod.POST)
  public ResponseEntity addRead(
      @Validated @RequestBody ReadNewsModel model) {

    userService.readNews(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getId()
    );

    return ResponseHelper.response(true);
  }

  ///////////////////daily reward////////////////////////////////
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/daily-reward/claim", method = RequestMethod.POST)
  public ResponseEntity claimDailyReward(
      @RequestParam(required = false, defaultValue = "") String username) {
    return ResponseHelper.response(userService.useDailyReward(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        username
    ));
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/add-social-net-follow", method = RequestMethod.POST)
  public ResponseEntity addSocialNetFollow(@Validated @RequestBody AddSocialNetFollowModel model) {
    return ResponseHelper.response(userService.addSocialNetworkFollowToProfile(
        currentContextService.getCurrentUuId(),
        model.getUsername(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getSocialNetworkName(),
        model.getCurrentAvatarIds(),
        model.getCurrentLevel(),
        model.getEndVipTime()
        )
    );
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/see-third-party-advertisement", method = RequestMethod.POST)
  public ResponseEntity seeThirdPartAdvertisement(
      @Validated @RequestBody AddSeeAdvertisementModel model) {
    return ResponseHelper.response(userService.addSeeThirdPartyAdvertisement(
        model.getUsername(),
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getThirdPartPackageName(),
        model.getCurrentAvatarIds(),
        model.getCurrentLevel(),
        model.getEndVipTime()
        )
    );
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/update-level", method = RequestMethod.POST)
  public ResponseEntity updateLevel() {
    return ResponseHelper.response(userService.updateLevel(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket()

    ));

  }
}
