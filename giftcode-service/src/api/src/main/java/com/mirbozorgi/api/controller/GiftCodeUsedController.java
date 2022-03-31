package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.UseGiftCodeModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.UsedGiftCodeService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuIdDefaultMarket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/using-gift-code")
public class GiftCodeUsedController {

  @Autowired
  private UsedGiftCodeService usedGiftCodeService;


  @Autowired
  private CurrentContextService currentContextService;

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/use", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody UseGiftCodeModel model) {
    return ResponseHelper.response(usedGiftCodeService.add(
        currentContextService.getCurrentUuId(),
        model.getCode(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getCurrentLevel(),
        model.getCurrentAvatarIds(),
        model.getEndVipTime()
    ));
  }

  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll(
      @RequestParam(required = false) String code) {
    return ResponseHelper
        .response(usedGiftCodeService.getAll(
            code,
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentEnv(),
            currentContextService.getCurrentMarket(),
            currentContextService.getCurrentUuId()
        ));
  }


}
