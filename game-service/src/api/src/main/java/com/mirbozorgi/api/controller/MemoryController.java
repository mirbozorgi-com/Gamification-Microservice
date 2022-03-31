package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.GameMemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping(value = "/default-market/memory")
public class MemoryController {

  @Autowired
  private GameMemoryService gameMemoryService;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity getByName(
      @RequestParam String gamePackageName,
      @RequestParam String env) {
    return ResponseHelper.response(gameMemoryService.getDefaultMarket(gamePackageName, env));
  }


}
