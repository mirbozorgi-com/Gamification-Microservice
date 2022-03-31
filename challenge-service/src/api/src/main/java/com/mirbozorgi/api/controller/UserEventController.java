package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.user_event.ActionListModel;
import com.mirbozorgi.api.model.user_event.ActionModel;
import com.mirbozorgi.api.model.user_event.MakeUserEventLoseOrWinModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.CastToUserStageEventEnumService;
import com.mirbozorgi.business.service.UserEventService;
import com.mirbozorgi.core.constant.ClientUserEventStage;
import com.mirbozorgi.core.constant.UserEventStage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuIdDefaultMarket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event/user")
public class UserEventController {

  @Autowired
  private UserEventService userEventService;

  @Autowired
  private CastToUserStageEventEnumService castToUserStageEventEnumService;

  @Autowired
  private CurrentContextService currentContextService;

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/join", method = RequestMethod.POST)
  public ResponseEntity join(@RequestBody ActionListModel model,
      @RequestParam(value = "userEventStages")
          ClientUserEventStage[] userEventStages) {
    List<ClientUserEventStage> userEventStageList = new ArrayList<>(Arrays.asList(userEventStages));
    List<UserEventStage> newUserEventStageList = castToUserStageEventEnumService
        .makeUserEventStageList(userEventStageList);
    return ResponseHelper.response(userEventService.join(
        currentContextService.getCurrentUuId(),
        model.getGlobalUniqueId(),
        model.getNotificationToken(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getUserEventJoinData(),
        newUserEventStageList));
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/claim", method = RequestMethod.POST)
  public ResponseEntity claim(@RequestBody ActionModel model) {
    userEventService.claim(currentContextService.getCurrentUuId(),
        model.getGlobalUniqueId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getEventId());
    return ResponseHelper.response(true);
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/close", method = RequestMethod.POST)
  public ResponseEntity close(@RequestBody ActionModel model) {
    userEventService.close(
        currentContextService.getCurrentUuId(),
        model.getGlobalUniqueId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getEventId(),
        model.getWin());
    return ResponseHelper.response(true);
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/make-winner-or-loser", method = RequestMethod.POST)
  public ResponseEntity changeStatusOfUserEvent(@RequestBody MakeUserEventLoseOrWinModel model) {
    userEventService.makeUserWinnerOrLoser(
        currentContextService.getCurrentUuId(),
        model.getGlobalUniqueId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getEventId(),
        model.getWin(),
        model.getEventRepetitionUuId());
    return ResponseHelper.response(true);
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/claim-and-close", method = RequestMethod.POST)
  public ResponseEntity closeAndClaim(@RequestBody ActionModel model) {
    userEventService.closeAndClaim(currentContextService.getCurrentUuId(),
        model.getGlobalUniqueId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getEventId(),
        model.getWin());
    return ResponseHelper.response(true);
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-history", method = RequestMethod.GET)
  public ResponseEntity getHistory(
      @RequestParam(required = false) String globalUniqueId) {
    return ResponseHelper
        .response(userEventService.getHistory(
            currentContextService.getCurrentUuId(),
            globalUniqueId,
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentEnv(),
            currentContextService.getCurrentMarket()));
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(
      @RequestParam(required = false) String globalUniqueId,
      @RequestParam(value = "userEventStages")
          ClientUserEventStage[] userEventStages) {
    List<ClientUserEventStage> userEventStageList = new ArrayList<>(Arrays.asList(userEventStages));
    List<UserEventStage> newUserEventStageList = castToUserStageEventEnumService
        .makeUserEventStageList(userEventStageList);
    return ResponseHelper
        .response(userEventService.getAllAggregate(
            currentContextService.getCurrentUuId(),
            globalUniqueId,
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentEnv(),
            currentContextService.getCurrentMarket(),
            newUserEventStageList
        ));
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-with-event-data", method = RequestMethod.GET)
  public ResponseEntity getWithEventData(
      @RequestParam(required = false) String globalUniqueId,
      @RequestParam(value = "userEventStages")
          ClientUserEventStage[] userEventStages) {
    List<ClientUserEventStage> userEventStageList = new ArrayList<>(Arrays.asList(userEventStages));
    List<UserEventStage> newUserEventStageList = castToUserStageEventEnumService
        .makeUserEventStageList(userEventStageList);
    return ResponseHelper
        .response(userEventService.getAllAggregateWithEventData(
            currentContextService.getCurrentUuId(),
            globalUniqueId,
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentEnv(),
            currentContextService.getCurrentMarket(),
            newUserEventStageList));
  }

}



