package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.controller.base.BaseController;
import com.mirbozorgi.api.model.AddGameModel;
import com.mirbozorgi.api.model.UpdateGameModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.GameService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GameProperties;
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
@RequestMapping(value = "/game")
public class GameController extends BaseController {

  @Autowired
  private GameService gameService;

  @Autowired
  private CurrentContextService currentContextService;

  @SuperAdmin
  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(@RequestParam int id) {
    return ResponseHelper.response(gameService.getById(id));
  }


  @GameProperties
  @RequestMapping(value = "/get-by", method = RequestMethod.GET)
  public ResponseEntity getByPackageName(@RequestParam String gamePackageName,
      @RequestParam String env) {
    return ResponseHelper.response(gameService.getByPackageNameAndEnv(
        gamePackageName,
        env
    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll() {
    return ResponseHelper.response(gameService.getAll());
  }

  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@RequestBody AddGameModel model) {
    return ResponseHelper.response(gameService.save(
        model.getName(),
        currentContextService.getCurrentGamePackageName(),
        model.isActive(),
        model.isHaveUserLogin(),
        currentContextService.getCurrentEnv(),
        model.getLastVersion(),
        model.getSupportedVersion(),
        model.getInstagram(),
        model.getTelegram(),
        model.getFacebook(),
        model.getTwitter(),
        model.getLinkedin(),
        model.getMarkets(),
        model.getDefaultMarketId(),
        model.getDefaultGem(),
        model.getDefaultGold(),
        model.getDefaultLevel(),
        model.getDefaultXp()
    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(@RequestBody UpdateGameModel model) {
    return ResponseHelper.response(gameService.update(
        model.getId(),
        model.getName(),
        currentContextService.getCurrentGamePackageName(),
        model.isActive(),
        model.isHaveUserLogin(),
        currentContextService.getCurrentEnv(),
        model.getLastVersion(),
        model.getSupportedVersion(),
        model.getInstagram(),
        model.getTelegram(),
        model.getFacebook(),
        model.getTwitter(),
        model.getLinkedin(),
        model.getMarkets(),
        model.getDefaultMarketId(),
        model.getDefaultGem(),
        model.getDefaultGold(),
        model.getDefaultLevel(),
        model.getDefaultXp()
    ));
  }
}
