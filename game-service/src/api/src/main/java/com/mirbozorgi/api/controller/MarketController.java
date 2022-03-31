package com.mirbozorgi.api.controller;


import com.mirbozorgi.api.model.AddMarketModel;
import com.mirbozorgi.api.model.DeleteModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.MarketService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/market")
public class MarketController {


  @Autowired
  private MarketService marketService;

  @Autowired
  private CurrentContextService currentContextService;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(@RequestParam int id) {
    return ResponseHelper.response(marketService.findById(id));
  }

  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll() {
    return ResponseHelper.response(marketService.findAll());
  }


  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody AddMarketModel model) {
    return ResponseHelper.response(marketService.save(
        model.getName(),
        model.getType(),
        model.getClientId(),
        model.getClientSecret(),
        model.getRefreshToken(),
        model.getRedirectUrl(),
        model.isByPass(),
        model.isJustLengthCheck(),
        model.getMaxLengthVerification(),
        model.isActive(),
        model.getVerificationUrl(),
        model.getRefreshTokenUrl(),
        model.getRefreshTokenTimeOut(),
        model.getAccessToken(),
        model.getMarketUrl(),
        model.getTelegramUrl(),
        model.getInstagramUrl(),
        model.getLastVersion(),
        model.getSupportedVersion()
    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity delete(@RequestBody DeleteModel model) {
    marketService.delete(model.getId());
    return ResponseHelper.response(true);
  }

  @RequestMapping(value = "/get-by-name", method = RequestMethod.GET)
  public ResponseEntity getByName(@RequestParam String name) {
    return ResponseHelper.response(marketService.findByName(name));
  }

  @RequestMapping(value = "/get-by-name-full-response", method = RequestMethod.GET)
  public ResponseEntity getByNameFullResponse(@RequestParam String name) {
    return ResponseHelper.response(marketService.findByNameFullResponse(name));
  }


}
