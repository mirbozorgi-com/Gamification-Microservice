//package com.mirbozorgi.api.controller;
//
//import com.mirbozorgi.api.model.AddUserChallengeModel;
//import com.mirbozorgi.api.util.helper.ResponseHelper;
//import com.mirbozorgi.business.service.UserChallengeService;
//import mirbozorgi.base.context.CurrentContextService;
//import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/user-challenge")
//public class  UserChallengeController {
//
//  @Autowired
//  private UserChallengeService userChallengeService;
//
//  @Autowired
//  private CurrentContextService currentContextService;
//
//
//  @GamePropertiesWithUuId
//  @RequestMapping(value = "/add", method = RequestMethod.POST)
//  public ResponseEntity add(@Validated @RequestBody AddUserChallengeModel model) {
//    return ResponseHelper.response(userChallengeService.addOrUpdate(
//        model.getChallengeId(),
//        currentContextService.getCurrentUuId(),
//        currentContextService.getCurrentGamePackageName(),
//        currentContextService.getCurrentEnv(),
//        currentContextService.getCurrentMarket()
//    ));
//  }
//
//  @GamePropertiesWithUuId
//  @RequestMapping(value = "/get", method = RequestMethod.GET)
//  public ResponseEntity get(
//      @RequestParam String challengeId) {
//    return ResponseHelper.response(userChallengeService.get(
//        challengeId,
//        currentContextService.getCurrentUuId(),
//        currentContextService.getCurrentGamePackageName(),
//        currentContextService.getCurrentEnv(),
//        currentContextService.getCurrentMarket()
//    ));
//  }
//
//  @GamePropertiesWithUuId
//  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
//  public ResponseEntity getAll(
//      @RequestParam(required = false) String challengeId,
//      @RequestParam(required = false) Boolean claim,
//      @RequestParam(required = false) Boolean active) {
//    return ResponseHelper.response(userChallengeService.getAll(
//        challengeId,
//        currentContextService.getCurrentUuId(),
//        currentContextService.getCurrentGamePackageName(),
//        currentContextService.getCurrentEnv(),
//        currentContextService.getCurrentMarket(),
//        claim,
//        active
//    ));
//  }
//  @GamePropertiesWithUuId
//  @RequestMapping(value = "/inc-score", method = RequestMethod.GET)
//  public ResponseEntity incScore(
//      @RequestParam String challengeId,
//      @RequestParam int score) {
//
//    return ResponseHelper.response(userChallengeService.incScore(
//        challengeId,
//        currentContextService.getCurrentUuId(),
//        currentContextService.getCurrentGamePackageName(),
//        currentContextService.getCurrentEnv(),
//        currentContextService.getCurrentMarket(),
//        score
//    ));
//
//
//  }
//  @GamePropertiesWithUuId
//  @RequestMapping(value = "/update-banned", method = RequestMethod.GET)
//  public ResponseEntity updateBanned(
//      @RequestParam String challengeId,
//      @RequestParam boolean banned) {
//
//    userChallengeService.updateBanned(
//        challengeId,
//        currentContextService.getCurrentUuId(),
//        currentContextService.getCurrentGamePackageName(),
//        currentContextService.getCurrentEnv(),
//        currentContextService.getCurrentMarket(),
//        banned);
//    return ResponseHelper.response(true);
//
//  }
//  @GamePropertiesWithUuId
//  @RequestMapping(value = "/update-claim", method = RequestMethod.GET)
//  public ResponseEntity updateClaimed(
//      @RequestParam String challengeId) {
//
//    return ResponseHelper.response(userChallengeService.updateClaimed(
//        challengeId,
//        currentContextService.getCurrentUuId(),
//        currentContextService.getCurrentGamePackageName(),
//        currentContextService.getCurrentEnv(),
//        currentContextService.getCurrentMarket()));
//  }
//
//}
