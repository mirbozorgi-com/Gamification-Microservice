package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.UpdateSocialFollowRewardModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.SocialNetWorksFollowRewardService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GameProperties;
import mirbozorgi.base.context.aop.anotions.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/social-network-follow-reward")
public class SocialNetWorkFollowRewardController {

  @Autowired
  private CurrentContextService currentContextService;

  @Autowired
  private SocialNetWorksFollowRewardService socialNetWorksFollowRewardService;

  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(@Validated @RequestBody UpdateSocialFollowRewardModel model) {
    return ResponseHelper.response(socialNetWorksFollowRewardService.update(
        model.getUrl(),
        model.getGem(),
        model.getGold(),
        model.getLevel(),
        model.getXp(),
        model.isHamiAdded(),
        model.getAddedVipPeriodTime(),
        model.getAvatarIds(),
        model.getNameOfSocialNet(),
        model.getGameId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv()
    ));
  }


  @GameProperties
  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll() {
    return ResponseHelper.response(socialNetWorksFollowRewardService.getAll(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv()));
  }

  @GameProperties
  @RequestMapping(value = "/get-all-networks", method = RequestMethod.GET)
  public ResponseEntity getAllNetworks() {
    return ResponseHelper.response(socialNetWorksFollowRewardService.getAllNetworks(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv()));
  }
}
