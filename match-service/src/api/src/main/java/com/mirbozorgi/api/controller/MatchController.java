package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.AddModel;
import com.mirbozorgi.api.model.DeleteModel;
import com.mirbozorgi.api.model.UpdateModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.MatchService;
import com.mirbozorgi.business.service.SerializerService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuIdDefaultMarket;
import mirbozorgi.base.context.aop.anotions.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/match")
public class MatchController {

  @Autowired
  private MatchService matchService;

  @Autowired
  private SerializerService serializerService;

  @Autowired
  private CurrentContextService currentContextService;

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getBy(
      @RequestParam(required = false) String name,
      @RequestParam(required = false, defaultValue = "0") Boolean active) {
    return ResponseHelper.response(matchService.findAll(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        name,
        active
    ));
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-all-for-players", method = RequestMethod.GET)
  public ResponseEntity getAllForPlayers(
      @RequestParam(required = false) String name,
      @RequestParam(required = false, defaultValue = "1") Boolean active) {
    return ResponseHelper.response(matchService.findAllForPlayers(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        name,
        active
    ));
  }

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(
      @RequestParam String id) {
    return ResponseHelper
        .response(matchService.findById(id));
  }

  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@RequestBody AddModel model) {
    return ResponseHelper.response(matchService.save(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getName(),
        model.getStartTime(),
        model.getEndTime(),
        model.getMaxScorePerUpdate(),
        model.getMinScorePerUpdate(),
        model.getMinSecondBetweenTwoUpdatingScore(),
        model.getMaxSecondBetweenTwoUpdatingScore(),
        model.getLimitErrorForUpdateRequestPerPeriod(),
        model.getMaxPlayerInMatch(),
        model.getMinPlayerInMatch(),
        model.getMinClientVersion(),
        model.getMaxClientVersion(),
        StringUtils.isEmpty(model.getReward())
            ? null
            : serializerService.toObj(model.getReward(), Object.class),
        StringUtils.isEmpty(model.getConfig())
            ? null
            : serializerService.toObj(model.getConfig(), Object.class),
        model.getMaxXpForOffline(),
        model.getMinXpForOffline(),
        model.getGemCost(),
        model.getGoldCost(),
        model.getGemWin(),
        model.getGoldWin(),
        model.getWinXpLooser(),
        model.getWinXpWinner(),
        model.getWinnerXpVipCoefficient(),
        model.getLooserXpVipCoefficient(), model.getGemBonus(),
        model.getGoldBonus(),
        model.getXpBonus()

    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(@RequestBody UpdateModel model) {
    return ResponseHelper.response(matchService.update(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getName(),
        model.getStartTime(),
        model.getEndTime(),
        model.getMaxScorePerUpdate(),
        model.getMinScorePerUpdate(),
        model.getMinSecondBetweenTwoUpdatingScore(),
        model.getMaxSecondBetweenTwoUpdatingScore(),
        model.getLimitErrorForUpdateRequestPerPeriod(),
        model.getMaxPlayerInMatch(),
        model.getMinPlayerInMatch(),
        model.getMinClientVersion(),
        model.getMaxClientVersion(),
        StringUtils.isEmpty(model.getReward())
            ? null
            : serializerService.toObj(model.getReward(), Object.class),
        StringUtils.isEmpty(model.getConfig())
            ? null
            : serializerService.toObj(model.getConfig(), Object.class),
        model.getMaxXpForOffline(),
        model.getMinXpForOffline(),
        model.getGemCost(),
        model.getGoldCost(),
        model.getGemWin(),
        model.getGoldWin(),
        model.getWinXpLooser(),
        model.getWinXpWinner(),
        model.getWinnerXpVipCoefficient(),
        model.getLooserXpVipCoefficient(), model.getGemBonus(),
        model.getGoldBonus(),
        model.getXpBonus(),
        model.getId()

    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity delete(@Validated @RequestBody DeleteModel model) {
    matchService.delete(model.getId());
    return ResponseHelper.response(true);
  }


}
