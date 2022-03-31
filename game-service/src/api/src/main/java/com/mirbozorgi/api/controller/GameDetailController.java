package com.mirbozorgi.api.controller;


import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.GameDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game-detail")
public class GameDetailController {

  @Autowired
  private GameDetailService gameDetailService;


  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(@RequestParam int id) {
    return ResponseHelper.response(gameDetailService.getById(id));
  }


  @RequestMapping(value = "/get-by", method = RequestMethod.GET)
  public ResponseEntity getByPublicKey(@RequestParam String publicKey) {
    return ResponseHelper.response(gameDetailService.getByPublicKey(publicKey));
  }


}
