package com.mirbozorgi.api.controller;


import com.mirbozorgi.api.model.AddAvatarModel;
import com.mirbozorgi.api.model.AvatarModel;
import com.mirbozorgi.api.model.WalletChangeModel;
import com.mirbozorgi.api.model.WalletOfflineModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.UserService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuIdDefaultMarket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

  @Autowired
  private UserService userService;

  @Autowired
  private CurrentContextService currentContextService;

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/change", method = RequestMethod.POST)
  public ResponseEntity walletChange(@RequestBody @Validated WalletChangeModel model,
      @RequestParam(required = false, defaultValue = "") String uuid,
      @RequestHeader String apiKey) {
    return ResponseHelper
        .response(userService.walletChange(
            apiKey,
            uuid,
            currentContextService.getCurrentUuId(),
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentEnv(),
            currentContextService.getCurrentMarket(),
            model.getGem(),
            model.getGold(),
            model.getLevel(),
            model.getXp(),
            model.getCurrentAvatarIds(),
            model.getCurrentLevel(),
            model.getGemBuy(),
            model.getEndVipTime()
        ));
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get() {
    return ResponseHelper
        .response(userService.getWallet(
            currentContextService.getCurrentUuId(),
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentEnv(),
            currentContextService.getCurrentMarket()
        ));
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/offline-change", method = RequestMethod.POST)
  public ResponseEntity walletChangeOffline(@RequestBody @Validated WalletOfflineModel model) {
    return ResponseHelper
        .response(userService.walletOfflineChange(
            currentContextService.getCurrentUuId(),
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentEnv(),
            currentContextService.getCurrentMarket(),
            model.getGem(),
            model.getGold(),
            model.getXp()
        ));
  }


  ///////////////////////////avatar//////////////////////////////
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/add-avatar", method = RequestMethod.POST)
  public ResponseEntity addAvatar(@Validated @RequestBody AddAvatarModel model) {
    userService.addAvatarPurchase(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getAvatarIds()
    );
    return ResponseHelper.response(true);

  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/set-avatar", method = RequestMethod.POST)
  public ResponseEntity setAvatar(@Validated @RequestBody AvatarModel model) {
    userService.setAvatarId(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getAvatarIds()
    );
    return ResponseHelper.response(true);

  }

  ////////////////////////level////////////////////////////////
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/check-level-update", method = RequestMethod.POST)
  public ResponseEntity levelUpdate() {
    userService.updateLevel(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket());
    return ResponseHelper.response(true);

  }

}
