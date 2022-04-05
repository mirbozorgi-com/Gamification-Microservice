package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.UserService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuIdDefaultMarket;
import mirbozorgi.base.domain.user.UpdateUserStatisticModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/statistic")
public class StatisticController {


  @Autowired
  private UserService userService;

  @Autowired
  private CurrentContextService currentContextService;

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/update-user-statistic", method = RequestMethod.POST)
  public ResponseEntity statistic(@Validated @RequestBody UpdateUserStatisticModel model) {
    userService.updateUserStatistic(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getIncHamiCount(),
        model.getIncarsalanParticipated(),
        model.getIncInvitations(),
        model.getIncGemSpent(),
        model.getIncWin(),
        model.getIncLose()
    );
    return ResponseHelper.response(true);
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-user-statistic", method = RequestMethod.POST)
  public ResponseEntity getStatistic() {

    return ResponseHelper.response(userService.getUserStatistic(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket()
    ));
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/inc-the-most-xp-weekly", method = RequestMethod.POST)
  public ResponseEntity incXpWeekly() {

    userService.updateFirstWeeklyXp(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        1
    );
    return ResponseHelper.response(true);

  }

}
