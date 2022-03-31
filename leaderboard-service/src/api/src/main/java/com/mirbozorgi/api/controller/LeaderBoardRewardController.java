package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.ClaimLeaderBoardModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.api.model.AddRewardModel;
import com.mirbozorgi.api.model.DeleteModel;
import com.mirbozorgi.api.model.UpdateLeaderBoardReward;
import com.mirbozorgi.business.service.LeaderBoardRewardService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuIdDefaultMarket;
import mirbozorgi.base.context.aop.anotions.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/reward-of-leader-board")
public class LeaderBoardRewardController {

  @Autowired
  private LeaderBoardRewardService leaderBoardRewardService;

  @Autowired
  private CurrentContextService currentContextService;

  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@RequestBody AddRewardModel model) {
    return ResponseHelper.response(leaderBoardRewardService.add(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getType(),
        model.getGem(),
        model.getGold(),
        model.getLevel(),
        model.getXp(),
        model.getHamiAdded(),
        model.getAvatarIds(),
        model.getAddedVipPeriodTime()

    ));
  }


  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(@RequestBody UpdateLeaderBoardReward model) {
    return ResponseHelper.response(leaderBoardRewardService.update(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getType(),
        model.getWalletChanges()
    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity update(@RequestBody DeleteModel model) {
    leaderBoardRewardService.delete(model.getId());
    return ResponseHelper.response(true);
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll() {
    return ResponseHelper.response(leaderBoardRewardService.findBy(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket()
    ));
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/claim", method = RequestMethod.GET)
  public ResponseEntity claim(
      @RequestBody ClaimLeaderBoardModel model) {
    return ResponseHelper.response(leaderBoardRewardService.claim(
        model.getType(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentUuId(),
        model.getUsername(),
        model.getCurrentLevel(),
        model.getCurrentAvatarIds(),
        model.getEndVipTime()

    ));
  }

}
