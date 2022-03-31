package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.api.model.UpdateGemBuyModel;
import com.mirbozorgi.business.service.LeaderBoardService;
import com.mirbozorgi.business.service.PlayerGemBuyService;
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
@RequestMapping("/leader-board-gem-buy")
public class LeaderBoardGemBuyController {

  @Autowired
  private LeaderBoardService leaderBoardService;

  @Autowired
  private CurrentContextService currentContextService;

  @Autowired
  private PlayerGemBuyService playerGemBuyService;

  // client want to request
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity gemBuy(
      @RequestParam(required = false) int topNumber) {
    return ResponseHelper.response(leaderBoardService.theMostBuyerGem(
        topNumber,
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentEnv()
    ));
  }

  //for add every week first person and delete others
  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(method = RequestMethod.GET, value = "/history")
  public ResponseEntity getDayHistory(
      @RequestParam(required = false) Integer dayNumber) {
    return ResponseHelper.response(leaderBoardService.theMostWeeklyHistoryGem(
        dayNumber,
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentUuId()
    ));
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody UpdateGemBuyModel model) {
    playerGemBuyService.update(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getGem(),
        model.getUsername(),
        model.getAvatarIds(),
        model.getLevel(),
        model.getEndVipTime()
    );
    return ResponseHelper.response(true);

  }

}
