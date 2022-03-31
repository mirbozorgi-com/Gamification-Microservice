package com.mirbozorgi.api.controller;


import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.EventNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/notification")
public class EventNotificationController {


  @Autowired
  private EventNotificationService eventNotificationService;

  @RequestMapping(value = "/send-all", method = RequestMethod.POST)
  public ResponseEntity sendAll() {
    eventNotificationService.sendNotificationToAllUser();
    return ResponseHelper.response(true);
  }


}
