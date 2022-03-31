package com.mirbozorgi.security.controller;


import com.mirbozorgi.security.model.ChangePasswordModel;
import com.mirbozorgi.security.model.ForgetPassModel;
import com.mirbozorgi.security.model.LoginRequest;
import com.mirbozorgi.security.model.SecurityRequest;
import com.mirbozorgi.security.util.helper.ResponseHelper;
import com.mirbozorgi.security.service.SecurityService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GameProperties;
import mirbozorgi.base.context.aop.anotions.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

  @Autowired
  private SecurityService securityService;


  @Autowired
  private CurrentContextService currentContextService;

  @UUID
  @PostMapping("/register")
  public ResponseEntity signUp(
      @Validated @RequestBody SecurityRequest request) {

    return ResponseHelper
        .response(securityService.userRegister(
            request.getEmail(),
            request.getPassword(),
            request.getDeviceId(),
            request.getGlobalUniqueId(),
            currentContextService.getCurrentUuId(),
            request.getGamePackageName(),
            request.getEnv(),
            request.getMarketName()));

  }


  @PostMapping("/login")
  public ResponseEntity login(
      @Validated @RequestBody LoginRequest request) {

    return ResponseHelper
        .response(securityService.login(
            request.getEmail(),
            request.getPassword(),
            request.getGlobalUniqueId(),
            request.getDeviceId(),
            request.getGamePackageName(),
            request.getEnv(),
            request.getMarketName()
        ));
  }

  @PostMapping("/authorize")
  public ResponseEntity authorize(
      @RequestHeader String token,
      @RequestHeader String marketName,
      @RequestHeader String gamePackageName,
      @RequestHeader String env) {

    return ResponseHelper
        .response(securityService.authorize(
            token,
            gamePackageName,
            env,
            marketName));
  }

  @GameProperties
  @GetMapping("/get-deviceid")
  public ResponseEntity getDeviceId(
      @RequestParam String uuid) {
    return ResponseHelper
        .response(securityService.getDeviceId(uuid,
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentEnv(),
            currentContextService.getCurrentMarket()));
  }


  @GetMapping("/get-by")
  public ResponseEntity getByEmail(
      @RequestParam(required = false) String email,
      @RequestParam String gamePackageName,
      @RequestParam String env,
      @RequestParam String marketName,
      @RequestParam(required = false) String uuid) {
    return ResponseHelper
        .response(securityService.getBy(
            email,
            uuid,
            gamePackageName,
            env,
            marketName));
  }


  @GetMapping("/verify")
  public ResponseEntity verify(
      @RequestParam String email,
      @RequestParam String verificationCode,
      @RequestParam String gamePackageName,
      @RequestParam String env,
      @RequestParam String marketName) {

    securityService.verify(
        email,
        gamePackageName,
        env,
        marketName,
        verificationCode
    );
    return ResponseHelper.response(true);

  }


  @GetMapping("/regenrate-verification-code")
  public ResponseEntity regenrateVerificationCode(
      @RequestParam String email,
      @RequestParam String gamePackageName,
      @RequestParam String env,
      @RequestParam String marketName) {
    securityService.reGenerateVerificationCode(
        email,
        gamePackageName,
        env,
        marketName);
    return ResponseHelper.response(true);

  }


  @GetMapping("/get-forget-pass-token")
  public ResponseEntity getForgetPassToken(
      @RequestParam String email,
      @RequestParam String gamePackageName,
      @RequestParam String env,
      @RequestParam String marketName) {
    securityService.sendForgetPassToken(
        email,
        gamePackageName,
        env,
        marketName);
    return ResponseHelper.response(true);

  }


  @PostMapping("/forget-password")
  public ResponseEntity forgetPassword(
      @RequestBody @Validated ForgetPassModel model) {

    securityService.forgetPassword(
        model.getEmail(),
        model.getGamePackageName(),
        model.getEnv(),
        model.getMarketName(),
        model.getNewPassword(),
        model.getForgetPassToken()
    );
    return ResponseHelper.response(true);

  }


  @PostMapping("/change-password-in-game")
  public ResponseEntity forgetPassword(
      @RequestBody @Validated ChangePasswordModel model) {

    securityService.changePassword(
        model.getEmail(),
        model.getGamePackageName(),
        model.getEnv(),
        model.getMarketName(),
        model.getPassword(),
        model.getNewPassword()
    );
    return ResponseHelper.response(true);

  }


}
