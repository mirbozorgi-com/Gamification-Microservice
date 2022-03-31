package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.AddDailyRewardModel;
import com.mirbozorgi.api.model.DeleteModel;
import com.mirbozorgi.api.model.UpdateDailyRewardContinuesModel;
import com.mirbozorgi.api.model.UpdateDailyRewardModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.DailyRewardContinuesService;
import com.mirbozorgi.business.service.DailyRewardService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GameProperties;
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
@RequestMapping(value = "/daily-reward")
public class DailyRewardController {

  @Autowired
  private DailyRewardService dailyRewardService;

  @Autowired
  private CurrentContextService currentContextService;

  @Autowired
  private DailyRewardContinuesService dailyRewardContinuesService;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(@RequestParam int id) {
    return ResponseHelper.response(dailyRewardService.findById(id));
  }


  @RequestMapping(value = "/get-by", method = RequestMethod.GET)
  public ResponseEntity getByPackageName(@RequestParam String gamePackageName,
      @RequestParam String env) {
    return ResponseHelper.response(dailyRewardService.findBy(
        gamePackageName,
        env
    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody AddDailyRewardModel model) {
    return ResponseHelper.response(dailyRewardService.add(
        model.getGameId(),
        model.getGem(),
        Short.parseShort(model.getLevel()),
        model.getGold(),
        model.getXp(),
        model.getAvatarId(),
        model.getVipPeriodTime(),
        model.getAddHami()

    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(@RequestBody UpdateDailyRewardModel model) {
    return ResponseHelper.response(dailyRewardService.update(
        model.getId(),
        model.getGameId(),
        model.getGem(),
        Short.parseShort(model.getLevel()),
        model.getGold(),
        model.getXp(),
        model.getAvatarId(),
        model.getVipPeriodTime(),
        model.getAddHami()

    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity delete(@RequestBody DeleteModel model) {
    dailyRewardService.delete(model.getId());
    return ResponseHelper.response(true);
  }

  /////////////////////////////////continues////////////////////////////////////////////////////
  @SuperAdmin
  @RequestMapping(value = "/continues/update", method = RequestMethod.POST)
  public ResponseEntity updateContinues(@RequestBody UpdateDailyRewardContinuesModel model) {
    return ResponseHelper.response(dailyRewardContinuesService.update(
        model.getDailyRewardContinuesData(),
        model.getName(),
        model.getGameId()
    ));
  }


  @GameProperties
  @RequestMapping(value = "/continues/get-all-daily", method = RequestMethod.GET)
  public ResponseEntity getAllDailyContinues(
      @RequestParam(required = false, defaultValue = "DEF") String name) {
    return ResponseHelper.response(dailyRewardContinuesService.getAllFlow(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        name
    ));
  }

}
