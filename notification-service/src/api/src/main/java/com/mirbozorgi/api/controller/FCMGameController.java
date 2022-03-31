package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.FcmGameModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.FCMGameService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/game-fcm")
public class FCMGameController {

  @Autowired
  private FCMGameService fcmGameService;


  @Autowired
  private CurrentContextService currentContextService;

  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody FcmGameModel model) {
    return ResponseHelper.response(fcmGameService.add(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getPathOfJson()
    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(@Validated @RequestBody FcmGameModel model) {
    return ResponseHelper.response(fcmGameService.update(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getPathOfJson()
    ));
  }


  @SuperAdmin
  @RequestMapping(value = "/get-by", method = RequestMethod.GET)
  public ResponseEntity update() {
    return ResponseHelper.response(fcmGameService.findBy(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket()));
  }

  @SuperAdmin
  @RequestMapping(value = "/delete", method = RequestMethod.GET)
  public ResponseEntity delete() {
    fcmGameService.delete(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(
        ));
    return ResponseHelper.response(true);

  }

  @SuperAdmin
  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll() {
    return ResponseHelper.response(
        fcmGameService.findAll()
    );
  }


}
