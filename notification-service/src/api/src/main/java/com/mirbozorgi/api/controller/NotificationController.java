package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.AddModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.NotificationService;
import com.mirbozorgi.business.service.SerializerService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/notification")
public class NotificationController {

  @Autowired
  private NotificationService notificationService;

  @Autowired
  private SerializerService serializerService;

  @Autowired
  private CurrentContextService currentContextService;

  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody AddModel model) {
    return ResponseHelper.response(notificationService.save(
        model.getName(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        StringUtils.isEmpty(model.getConfig())
            ? null
            : serializerService.toObj(model.getConfig(), Object.class)
        ,
        model.getMinClientVersion(),
        model.getMaxClientVersion(),
        model.getRemoveAble(),
        model.getGem(),
        model.getGold(),
        model.getLevel(),
        model.getXp(),
        model.isHamiAded(),
        model.getAvatarIds(),
        model.getAddedVipPeriodTime(),
        model.getTitle(),
        model.getTopic(),
        model.getMessage(),
        model.getType(),
        model.getFcmSend()
    ));
  }


  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(@RequestParam String id) {
    return ResponseHelper
        .response(notificationService.findById(id));
  }

  @SuperAdmin
  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll(
      @RequestParam(required = false) String name
  ) {
    return ResponseHelper.response(
        notificationService.findAll(name,
            currentContextService.getCurrentEnv(),
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentMarket())
    );
  }


}
