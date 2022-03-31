package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.UpdateXpModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.LeaderBoardMemoryXpService;
import com.mirbozorgi.business.service.LeaderBoardService;
import com.mirbozorgi.business.service.PlayerXpService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuIdDefaultMarket;
import mirbozorgi.base.context.aop.anotions.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leader-board-xp")
public class LeaderBoardXpController {

  @Autowired
  private LeaderBoardService leaderBoardService;

  @Autowired
  private CurrentContextService currentContextService;

  @Autowired
  private PlayerXpService playerXpService;

  @Autowired
  private LeaderBoardMemoryXpService leaderBoardMemoryXpService;

  // client want to request
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity byXp(
      @RequestParam(required = false) int topNumber) {
    return ResponseHelper.response(leaderBoardService.byXp(
        topNumber,
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentEnv()
    ));
  }

  // client want to request
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/periodic", method = RequestMethod.GET)
  public ResponseEntity byTimeXp(
      @RequestParam(required = false) Integer topNumber) {
    return ResponseHelper.response(leaderBoardService.byXpLastSevenDays(
        topNumber,
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentUuId()
    ));
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(
      @Validated @RequestBody UpdateXpModel model) {
    playerXpService.update(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getXpGlobal(),
        model.getXp(),
        model.getLevel(),
        model.getUsername(),
        model.getAvatarIds(),
        model.getEndVipTime()
    );
    return ResponseHelper.response(true);
  }

  @SuperAdmin
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity delete() {
    playerXpService.resetWithTime(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket()
    );
    return ResponseHelper.response(true);
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-position-weekly", method = RequestMethod.GET)
  public ResponseEntity getPosition() {
    return ResponseHelper.response(leaderBoardMemoryXpService.getPosition(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentUuId()));

  }


}
