package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.DeleteModel;
import com.mirbozorgi.api.model.FCMGameMessageModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.FCMGameMessageService;
import mirbozorgi.base.constanct.EnumKeyFCM;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GameProperties;
import mirbozorgi.base.context.aop.anotions.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/game-fcm-message")
public class FCMGameMessageController {

  @Autowired
  private FCMGameMessageService fcmGameMessageService;


  @Autowired
  private CurrentContextService currentContextService;

  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody FCMGameMessageModel model) {
    return ResponseHelper.response(fcmGameMessageService.add(
        model.getEnumKeyFCM(),
        model.getTopic(),
        model.getMessage(),
        model.getTitle(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket()
    ));
  }

  @GameProperties
  @RequestMapping(value = "/get-by", method = RequestMethod.GET)
  public ResponseEntity getBy(@RequestParam EnumKeyFCM enumKeyFCM) {
    return ResponseHelper.response(fcmGameMessageService.findBy(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        enumKeyFCM));
  }

  @SuperAdmin
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity delete(@RequestBody DeleteModel model) {
    fcmGameMessageService.delete(model.getId());
    return ResponseHelper.response(true);

  }

  @GameProperties
  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll() {
    return ResponseHelper.response(
        fcmGameMessageService.findAll(
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentEnv(),
            currentContextService.getCurrentMarket())
    );
  }

}
