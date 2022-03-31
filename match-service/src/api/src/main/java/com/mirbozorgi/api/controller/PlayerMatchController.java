package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.FinishMatchModel;
import com.mirbozorgi.api.model.JoinModel;
import com.mirbozorgi.api.model.OfflineJoinModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.PlayerMatchService;
import com.mirbozorgi.business.service.SerializerService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuIdDefaultMarket;
import mirbozorgi.base.context.aop.anotions.UUID;
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
@RequestMapping("/player-match")
public class PlayerMatchController {

  @Autowired
  private PlayerMatchService playerMatchService;

  @Autowired
  private SerializerService serializerService;

  @Autowired
  private CurrentContextService currentContextService;

  //TODO check shawad , moshkel dare
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/join", method = RequestMethod.POST)
  public ResponseEntity join(@RequestBody @Validated JoinModel model) {
    return ResponseHelper.response(playerMatchService.join(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket(),
        model.getMatchName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentUuId(),
        model.getUsername(),
        model.getConfig()));
  }

  @UUID
  @RequestMapping(value = "/offline-join", method = RequestMethod.POST)
  public ResponseEntity offlineJoin(@RequestBody @Validated OfflineJoinModel model) {
    return ResponseHelper.response(playerMatchService.offlineJoin(
        currentContextService.getCurrentUuId(),
        StringUtils.isEmpty(model.getConfig())
            ? null
            : serializerService.toObj(model.getConfig(), Object.class),
        model.getNumberOfPlayerWanted(),
        model.getMatchId(),
        model.getCurrentLevel(),
        model.getCurrentAvatarIds(),
        model.getUsername()
    ));
  }

  @UUID
  @RequestMapping(value = "/load", method = RequestMethod.GET)
  public ResponseEntity load(@RequestParam String matchId) {
    return ResponseHelper.response(playerMatchService.load(
        matchId,
        currentContextService.getCurrentUuId()
    ));
  }

  @UUID
  @RequestMapping(value = "/finish", method = RequestMethod.POST)
  public ResponseEntity offlineJoin(@RequestBody @Validated FinishMatchModel model) {
    return ResponseHelper.response(playerMatchService.finish(
        model.getMatchId(),
        currentContextService.getCurrentUuId(),
        model.getWin(),
        model.getCurrentLevel(),
        model.getCurrentAvatarIds(),
        model.getEndVipTime()
    ));
  }

}
