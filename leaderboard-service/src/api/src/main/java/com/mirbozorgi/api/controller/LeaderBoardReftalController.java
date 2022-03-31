package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.api.model.UpdateReferalModel;
import com.mirbozorgi.business.service.LeaderBoardService;
import com.mirbozorgi.business.service.PlayerReferalService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuIdDefaultMarket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leader-board-refral")
public class LeaderBoardReftalController {

  @Autowired
  private LeaderBoardService leaderBoardService;

  @Autowired
  private CurrentContextService currentContextService;

  @Autowired
  private PlayerReferalService playerReferalService;

  // client want to request
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity byRefral(
      @RequestParam(required = false) int topNumber) {
    return ResponseHelper.response(leaderBoardService.byMostRefralLastSevenDays(
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
      @Validated @RequestBody UpdateReferalModel model) {
    playerReferalService.update(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getQuantity(),
        model.getLevel(),
        model.getUsername(),
        model.getAvatarIds(),
        model.getEndVipTime()
    );
    return ResponseHelper.response(true);
  }

}
