package com.mirbozorgi.api.controller;


import com.mirbozorgi.api.controller.base.BaseController;
import com.mirbozorgi.api.model.AddSkuModel;
import com.mirbozorgi.api.model.UpdateSkuModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.SkuService;
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
@RequestMapping(value = "/sku")
public class SkuController extends BaseController {

  @Autowired
  private SkuService skuService;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(@RequestParam int id) {
    return ResponseHelper.response(skuService.get(id));
  }


  @RequestMapping(value = "/get-by", method = RequestMethod.GET)
  public ResponseEntity getBy(@RequestParam int gameId, @RequestParam String name) {
    return ResponseHelper.response(skuService.getByNameAndGameId(gameId, name));
  }


  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll(
      @RequestParam(required = false) Integer gameId
  ) {
    return ResponseHelper.response(skuService.getAll(gameId));
  }


  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody AddSkuModel model) {
    return ResponseHelper.response(skuService.save(
        model.getGameId(),
        model.getName(),
        model.getDescription(),
        model.getAmount(),
        model.getCurrency(),
        model.getActive(),
        model.getConsumable()
    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(@RequestBody UpdateSkuModel model) {
    return ResponseHelper.response(skuService.update(
        model.getId(),
        model.getGameId(),
        model.getName(),
        model.getDescription(),
        model.getAmount(),
        model.getCurrency(),
        model.getActive(),
        model.getConsumable()
    ));
  }

}
