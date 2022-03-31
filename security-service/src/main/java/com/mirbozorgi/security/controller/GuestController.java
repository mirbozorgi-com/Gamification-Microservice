package com.mirbozorgi.security.controller;

import com.mirbozorgi.security.model.LoginRequestModel;
import com.mirbozorgi.security.util.helper.ResponseHelper;
import com.mirbozorgi.security.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/guest")
public class GuestController {

  @Autowired
  private GuestService guestService;


  @PostMapping("/login")
  public ResponseEntity guestLogin(
      @Validated @RequestBody LoginRequestModel model) {
    return ResponseHelper.response(
        guestService.login(
            model.getDeviceId(),
            model.getGamePackageName(),
            model.getEnv(),
            model.getMarketName())
    );

  }

}
