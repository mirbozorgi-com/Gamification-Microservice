package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.AddModel;
import com.mirbozorgi.api.model.DeleteModel;
import com.mirbozorgi.api.model.UpdateModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.GiftCodeService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuId;
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
@RequestMapping("/gift-code")
public class GiftCodeController {

  @Autowired
  private GiftCodeService giftCodeService;

  @Autowired
  private CurrentContextService currentContextService;

  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody AddModel model) {
    return ResponseHelper.response(giftCodeService.save(
        model.getName(),
        model.getCode(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getWalletChange(),
        model.getExpireDate(),
        model.getMaxUseCountGlobal(),
        model.getActive(),
        model.getMinClientVersion(),
        model.getMaxClientVersion(),
        model.getMinLevelPermission(),
        model.getDaysAfterInstall()
    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(@RequestParam String id) {
    return ResponseHelper
        .response(giftCodeService.findById(id));
  }

  @GamePropertiesWithUuId
  @RequestMapping(value = "/get-by", method = RequestMethod.GET)
  public ResponseEntity getBy(
      @RequestParam String code) {
    return ResponseHelper
        .response(giftCodeService.findBy(code,
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentMarket(),
            currentContextService.getCurrentEnv()
        ));
  }


  @SuperAdmin
  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String code
  ) {
    return ResponseHelper.response(
        giftCodeService.findAll(
            name,
            code,
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentEnv(),
            currentContextService.getCurrentMarket()
        )
    );
  }

  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(@RequestBody UpdateModel model) {
    return ResponseHelper.response(giftCodeService.update(
        model.getId(),
        model.getName(),
        model.getCode(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getWalletChange(),
        model.getExpireDate(),
        model.getMaxUseCountGlobal(),
        model.getActive(),
        model.getMinClientVersion(),
        model.getMaxClientVersion(),
        model.getMinLevelPermission(),
        model.getDaysAfterInstall()

    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity delete(@Validated @RequestBody DeleteModel model) {
    giftCodeService.delete(model.getId());
    return ResponseHelper.response(true);
  }


}
