package com.mirbozorgi.api.controller;


import com.mirbozorgi.api.model.SpinModel;
import com.mirbozorgi.api.model.SpinSaveModel;
import com.mirbozorgi.api.model.UpdateItemsModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.SpinService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuIdDefaultMarket;
import mirbozorgi.base.context.aop.anotions.SuperAdmin;
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
@RequestMapping("/spin")
public class SpinController {

  @Autowired
  private CurrentContextService currentContextService;


  @Autowired
  private SpinService spinService;

  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity spin(@RequestBody SpinSaveModel model) {
    return ResponseHelper.response(spinService.addOrUpdate(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getKey(),
        model.getTimePeriod()
    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/update-items", method = RequestMethod.POST)
  public ResponseEntity itemUpdates(@RequestBody UpdateItemsModel model) {
    return ResponseHelper.response(spinService.updateItems(
        model.getKey(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getItemDatas()

    ));
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-all-key", method = RequestMethod.GET)
  public ResponseEntity getKeys() {
    return ResponseHelper.response(spinService.getKeys(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket()
    ));
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/user/get-all", method = RequestMethod.GET)
  public ResponseEntity getAllForUser(
      @RequestParam(required = false, defaultValue = "DEF") String key) {
    return ResponseHelper.response(spinService.getAllUserSpin(
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        key
    ));
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/get-all-item-by-key", method = RequestMethod.GET)
  public ResponseEntity getAllItemByKey(@RequestParam String key) {
    return ResponseHelper.response(spinService.getAllByKey(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        key
    ));
  }


  @GamePropertiesWithUuIdDefaultMarket
  @RequestMapping(value = "/rolling-spin", method = RequestMethod.POST)
  public ResponseEntity buy(@RequestBody SpinModel model) {
    return ResponseHelper.response(spinService.rolling(
        currentContextService.getCurrentUuId(),
        "",
        model.getUsername(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getKey(),
        model.getCurrentLevel(),
        model.getCurrentAvatarIds(),
        model.getEndVipTime()
    ));
  }

}
