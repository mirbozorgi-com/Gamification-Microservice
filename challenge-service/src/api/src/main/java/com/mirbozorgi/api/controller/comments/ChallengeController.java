//package com.mirbozorgi.api.controller;
//
//import com.mirbozorgi.api.model.AddModel;
//import com.mirbozorgi.api.model.DeleteModel;
//import com.mirbozorgi.api.model.UpdateModel;
//import com.mirbozorgi.api.util.helper.ResponseHelper;
//import com.mirbozorgi.business.service.ChallengeService;
//import com.mirbozorgi.business.service.SerializerService;
//import mirbozorgi.base.context.CurrentContextService;
//import mirbozorgi.base.context.aop.anotions.SuperAdmin;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/challenge")
//public class ChallengeController {
//
//  @Autowired
//  private ChallengeService challengeService;
//
//  @Autowired
//  private SerializerService serializerService;
//
//  @Autowired
//  private CurrentContextService currentContextService;
//
//  @RequestMapping(value = "/get-by", method = RequestMethod.GET)
//  public ResponseEntity getBy(
//      @RequestParam String name,
//      @RequestParam String gamePackageName,
//      @RequestParam String env,
//      @RequestParam String marketName) {
//    return ResponseHelper
//        .response(challengeService.findBy(
//            gamePackageName,
//            env,
//            marketName,
//            name
//        ));
//  }
//
//  @RequestMapping(value = "/get", method = RequestMethod.GET)
//  public ResponseEntity get(
//      @RequestParam String id) {
//    return ResponseHelper
//        .response(challengeService.findById(id));
//  }
//
//
//  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
//  public ResponseEntity getAll(
//      @RequestParam(required = false) String gamePackageName,
//      @RequestParam(required = false) String env,
//      @RequestParam(required = false) String marketName,
//      @RequestParam(required = false) String name,
//      @RequestParam(required = false) Boolean active
//  ) {
//    return ResponseHelper.response(challengeService.findAll(
//        gamePackageName,
//        env,
//        marketName,
//        name,
//        active
//    ));
//  }
//
//  @SuperAdmin
//  @RequestMapping(value = "/add", method = RequestMethod.POST)
//  public ResponseEntity add(@RequestBody AddModel model) {
//    return ResponseHelper.response(challengeService.save(
//        model.getName(),
//        model.getStartTime(),
//        model.getEndTime(),
//        model.getMaxScorePerUpdate(),
//        model.getMinScorePerUpdate(),
//        model.getSecondBetweenTwoUpdatingScore(),
//        model.getLimitForUpdateRequestPerPeriod(),
//        StringUtils.isEmpty(model.getReward())
//            ? null
//            : serializerService.toObj(model.getReward(), Object.class)
//        ,
//        model.getType(),
//        currentContextService.getCurrentGamePackageName(),
//        currentContextService.getCurrentEnv(),
//        currentContextService.getCurrentMarket(),
//        model.getAllMarketInclude(),
//        model.getMinClientVersion(),
//        model.getMaxClientVersion()
//    ));
//  }
//
//  @SuperAdmin
//  @RequestMapping(value = "/update", method = RequestMethod.POST)
//  public ResponseEntity update(@RequestBody UpdateModel model) {
//    return ResponseHelper.response(challengeService.update(
//        model.getId(),
//        model.getName(),
//        model.getStartTime(),
//        model.getEndTime(),
//        model.getMaxScorePerUpdate(),
//        model.getMinScorePerUpdate(),
//        model.getSecondBetweenTwoUpdatingScore(),
//        model.getLimitForUpdateRequestPerPeriod(),
//        StringUtils.isEmpty(model.getReward())
//            ? null
//            : serializerService.toObj(model.getReward(), Object.class)
//        ,
//        model.getType(),
//        currentContextService.getCurrentGamePackageName(),
//        currentContextService.getCurrentEnv(),
//        currentContextService.getCurrentMarket(),
//        model.getAllMarketInclude(),
//        model.getMinClientVersion(),
//        model.getMaxClientVersion()
//    ));
//  }
//
//  @SuperAdmin
//  @RequestMapping(value = "/delete", method = RequestMethod.POST)
//  public ResponseEntity delete(@Validated @RequestBody DeleteModel model) {
//    challengeService.delete(model.getId());
//    return ResponseHelper.response(true);
//  }
//
//
//  @RequestMapping(value = "/check-include-all-market", method = RequestMethod.GET)
//  public ResponseEntity isIncludeAllMarket(
//      @RequestParam String id) {
//    return ResponseHelper
//        .response(challengeService.isIncludeAllMarket(id));
//  }
//
//
//}
