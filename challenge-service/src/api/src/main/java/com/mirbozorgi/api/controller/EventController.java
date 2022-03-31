package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.DeleteModel;
import com.mirbozorgi.api.model.event.AddEventModel;
import com.mirbozorgi.api.model.event.TerminateEventModel;
import com.mirbozorgi.api.model.event.UpdateEventModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.EventService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GameProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/event")
public class EventController {

  @Autowired
  private EventService eventService;

  @Autowired
  private CurrentContextService currentContextService;

  @GameProperties
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@RequestBody AddEventModel model) {
    return ResponseHelper.response(eventService.add(
        model.getConfigVersion(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentEnv(),
        model.getName(),
        model.getPeriodTimeForMiddleJoinTillEnd(),
        model.getStates(),
        model.getRepetitions(),
        model.getVersionMetaData(),
        model.getEventEndType(),
        model.getClientEventType()));
  }

  @GameProperties
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(@RequestBody UpdateEventModel model) {
    return ResponseHelper.response(eventService.update(
        model.getConfigVersion(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentEnv(),
        model.getName(),
        model.getId(),
        model.getPeriodTimeForMiddleJoinTillEnd(),
        model.getStates(),
        model.getRepetitions(),
        model.getVersionMetaData(),
        model.getEventEndType(),
        model.getClientEventType()));
  }


  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(
      @RequestParam String id,
      @RequestParam String clientVersion) {
    return ResponseHelper
        .response(eventService.getById(
            id,
            clientVersion));
  }

  @GameProperties
  @RequestMapping(value = "/get-active", method = RequestMethod.GET)
  public ResponseEntity getActive(
      @RequestParam(required = false, defaultValue = "1") Boolean notStart,
      @RequestParam(required = false, defaultValue = "1") Boolean active,
      @RequestParam(required = false, defaultValue = "1") Boolean preActive,
      @RequestParam(required = false, defaultValue = "1") Boolean finish,
      @RequestParam(required = false, defaultValue = "1") Boolean terminated) {
    return ResponseHelper
        .response(eventService.getActiveAndPreActive(
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentEnv(),
            currentContextService.getCurrentMarket(),
            active,
            preActive,
            finish,
            terminated,
            notStart
        ));
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity delete(@RequestBody DeleteModel model) {
    eventService.delete(model.getId());
    return ResponseHelper.response(true);
  }

  @RequestMapping(value = "/terminate", method = RequestMethod.POST)
  public ResponseEntity terminate(@RequestBody TerminateEventModel model) {
    eventService.terminate(model.getId());
    return ResponseHelper.response(true);
  }

}
