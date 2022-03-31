package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.EventWinner.AddWinner;
import com.mirbozorgi.api.model.EventWinner.Getwinners;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.EventWinnerHistoryService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuIdDefaultMarket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/winner")
public class EventWinnersHistory {

  @Autowired
  private EventWinnerHistoryService eventWinnerHistoryService;


  @Autowired
  private CurrentContextService currentContextService;

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@RequestBody AddWinner model) {
    eventWinnerHistoryService.makeWinner(
        model.getEventId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getEventRepetitionRandomUuId(),
        currentContextService.getCurrentUuId(),
        model.getGlobalUniqueId());
    return ResponseHelper.response(true);
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get", method = RequestMethod.POST)
  public ResponseEntity get(@RequestBody Getwinners model) {

    return ResponseHelper.response(eventWinnerHistoryService.getEventWinner(
        model.getEventId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getEventRepetitionRandomUuId()
    ));
  }

}
