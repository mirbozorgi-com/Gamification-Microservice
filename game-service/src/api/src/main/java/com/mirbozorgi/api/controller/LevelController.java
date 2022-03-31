package com.mirbozorgi.api.controller;


import com.mirbozorgi.api.model.AddLevelModel;
import com.mirbozorgi.api.model.DeleteModel;
import com.mirbozorgi.api.model.UpdateLevelModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.LevelService;
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
@RequestMapping(value = "/level")
public class LevelController {

  @Autowired
  private LevelService levelService;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(@RequestParam int id) {
    return ResponseHelper.response(levelService.findById(id));
  }


  @RequestMapping(value = "/get-current-by-xp", method = RequestMethod.GET)
  public ResponseEntity getBy(
      @RequestParam String gamePackageName,
      @RequestParam String env,
      @RequestParam int currentXp) {
    return ResponseHelper.response(levelService.findByGameIdAndLimitation(
        gamePackageName,
        env,
        currentXp));
  }


  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll(
      @RequestParam Integer gameId
  ) {
    return ResponseHelper.response(levelService.findAll(gameId));
  }


  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody AddLevelModel model) {
    return ResponseHelper.response(levelService.save(
        model.getLevelName(),
        model.getGameId(),
        model.getMinXp(),
        model.getMaxXp(),
        model.getLevelNumber()
    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(@Validated @RequestBody UpdateLevelModel model) {
    return ResponseHelper.response(levelService.update(
        model.getId(),
        model.getLevelName(),
        model.getMinXp(),
        model.getMaxXp(),
        model.getLevelNumber()
    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity delete(@Validated @RequestBody DeleteModel model) {
    levelService.delete(model.getId());
    return ResponseHelper.response(true);

  }

}
