package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.AddAvatarModel;
import com.mirbozorgi.api.model.DeleteModel;
import com.mirbozorgi.api.model.UpdateAvatarModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.AvatarService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GameProperties;
import mirbozorgi.base.context.aop.anotions.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping(value = "/avatar")
public class AvatarController {

  @Autowired
  private AvatarService avatarService;

  @Autowired
  private CurrentContextService currentContextService;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(@RequestParam int id) {
    return ResponseHelper.response(avatarService.findById(id));
  }

  @SuperAdmin
  @RequestMapping(value = "/get-by", method = RequestMethod.GET)
  public ResponseEntity getBy(@RequestParam int gameId, @RequestParam String name) {
    return ResponseHelper.response(avatarService.findByNameAndGameId(name, gameId));
  }

  @GameProperties
  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll() {
    return ResponseHelper.response(avatarService.findAll(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv()
    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody AddAvatarModel model) {
    return ResponseHelper.response(avatarService.save(
        model.getGameId(),
        model.getName(),
        model.getAmount(),
        model.getCurrencyType(),
        model.getActive(),
        model.getAvatarType(),
        model.getFree(),
        model.getTypeForBuy()
    ));
  }


  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(@Validated @RequestBody UpdateAvatarModel model) {
    return ResponseHelper.response(avatarService.update(
        model.getId(),
        model.getName(),
        model.getAmount(),
        model.getCurrencyType(),
        model.getActive(),
        model.getAvatarType(),
        model.getFree(),
        model.getTypeForBuy()
    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity delete(@Validated @RequestBody DeleteModel model) {
    avatarService.delete(model.getId());
    return ResponseHelper.response(true);

  }


}
