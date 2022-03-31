package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.UpsertConfigModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.ConfigService;
import mirbozorgi.base.context.CurrentContextService;
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

@RestController
@RequestMapping("/config")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ConfigController {

  @Autowired
  private ConfigService configService;

  @Autowired
  private CurrentContextService currentContextService;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(
      @RequestParam String gamePackageName,
      @RequestParam String marketName,
      @RequestParam String env) {
    return ResponseHelper.response(
        configService.get(
            gamePackageName,
            env,
            marketName
        )
    );
  }

  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(
      @Validated @RequestBody UpsertConfigModel model) {
    return ResponseHelper.response(
        configService.upsert(
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentEnv(),
            currentContextService.getCurrentMarket(),
            model.getXpCronWeekDay(),
            model.getReferalCronWeekDay(),
            model.getGemCronWeekDay()
        )
    );
  }

}
