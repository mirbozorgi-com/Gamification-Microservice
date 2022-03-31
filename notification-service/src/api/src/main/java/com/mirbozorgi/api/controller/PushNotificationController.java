package com.mirbozorgi.api.controller;


import com.mirbozorgi.api.model.PushToAllFcmNotificationRequest;
import com.mirbozorgi.api.model.PushFCMModel;
import com.mirbozorgi.business.domain.PushNotificationRequest;
import com.mirbozorgi.business.domain.PushNotificationResponse;
import com.mirbozorgi.business.service.PushNotificationService;
import java.util.concurrent.ExecutionException;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GameProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping(value = "/push-notification")
public class PushNotificationController {

  @Autowired
  private PushNotificationService pushNotificationService;

  @Autowired
  private CurrentContextService currentContextService;

  @GameProperties
  @PostMapping("/uuid")
  public ResponseEntity byUuId(@RequestBody PushFCMModel model)
      throws ExecutionException, InterruptedException {
    pushNotificationService.byUuId(
        model.getTitle(),
        model.getMessage(),
        model.getTopic(),
        currentContextService.getCurrentUuId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentEnv()
    );
    return new ResponseEntity<>(
        new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."),
        HttpStatus.OK);
  }


  @GameProperties
  @PostMapping("/token")
  public ResponseEntity byToken(@RequestBody PushNotificationRequest model)
      throws ExecutionException, InterruptedException {
    pushNotificationService.byToken(
        model.getTitle(),
        model.getMessage(),
        model.getTopic(),
        model.getToken(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentMarket(),
        currentContextService.getCurrentEnv()
    );
    return new ResponseEntity<>(
        new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."),
        HttpStatus.OK);
  }


  @GameProperties
  @PostMapping("/add/fcm/all")
  public ResponseEntity addFcmToAll(@RequestBody PushToAllFcmNotificationRequest model) {
    pushNotificationService.addAll(
        model.getTitle(),
        model.getMessage(),
        model.getTopic(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv()
    );
    return new ResponseEntity<>(
        new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."),
        HttpStatus.OK);
  }


}
