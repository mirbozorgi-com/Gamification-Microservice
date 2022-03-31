package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.api.model.AddModel;
import com.mirbozorgi.business.service.LeaderBoardService;
import com.mirbozorgi.business.service.PlayerGameScoreService;
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
@RequestMapping("/leader-board")
public class LeaderBoardController {

  @Autowired
  private LeaderBoardService leaderBoardService;

  @Autowired
  private PlayerGameScoreService playerGameScoreService;

  @Autowired
  private CurrentContextService currentContextService;

  //internal requests
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody AddModel model) {
    return ResponseHelper.response(playerGameScoreService.add(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentEnv(),
        model.getChallengeId(),
        currentContextService.getCurrentUuId(),
        model.getUsername(),
        model.getAllMarketInclude()
    ));
  }

  //internal requests
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/inc-score", method = RequestMethod.GET)
  public ResponseEntity incScore(
      @RequestParam String challengeId,
      @RequestParam int score) {
    return ResponseHelper.response(playerGameScoreService.incGameScore(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentEnv(),
        challengeId,
        currentContextService.getCurrentUuId(),
        score
    ));
  }

  // client want to request
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/score", method = RequestMethod.GET)
  public ResponseEntity byScore(
      @RequestParam(required = false) int topNumber,
      @RequestParam String challengeId) {
    return ResponseHelper.response(leaderBoardService.byScore(
        topNumber,
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentEnv(),
        challengeId
    ));
  }

  // client want to request
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-position/score", method = RequestMethod.GET)
  public ResponseEntity getPos(
      @RequestParam String challengeId) {
    return ResponseHelper.response(playerGameScoreService.getPositionInLeaderBoard(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentEnv(),
        challengeId,
        currentContextService.getCurrentUuId()));
  }


  @RequestMapping(value = "/reset/manually", method = RequestMethod.GET)
  public ResponseEntity manually(
      @RequestParam String gamePackageName,
      @RequestParam String env,
      @RequestParam String marketName,
      @RequestParam String apiKey) {
    return ResponseHelper.response(leaderBoardService.reset(
        gamePackageName,
        marketName,
        env,
        apiKey
    ));
  }
}
