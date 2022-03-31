package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.AddConstCohortModel;
import com.mirbozorgi.api.model.DeleteModel;
import com.mirbozorgi.api.model.UpdateCohortModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.ConstCohortService;
import com.mirbozorgi.business.service.SerializerService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/const-cohort")
public class ConstCohortController {

  @Autowired
  private ConstCohortService constCohortService;

  @Autowired
  private SerializerService serializerService;

  @Autowired
  private CurrentContextService currentContextService;


  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(@RequestParam String id) {
    return ResponseHelper.response(constCohortService.findById(id));
  }


  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll() {
    return ResponseHelper.response(constCohortService.findAll());
  }

  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody AddConstCohortModel model) {
    return ResponseHelper.response(
        constCohortService.save(
            currentContextService.getCurrentGamePackageName(),
            model.getName(),
            StringUtils.isEmpty(model.getConfig())
                ? null
                : serializerService.toObj(model.getConfig(), Object.class)
        )
    );
  }

  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(@RequestBody UpdateCohortModel model) {
    return ResponseHelper.response(constCohortService.update(
        model.getId(),
        currentContextService.getCurrentGamePackageName(),
        model.getName(),
        StringUtils.isEmpty(model.getConfig())
            ? null
            : serializerService.toObj(model.getConfig(), Object.class)

    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity delete(@Validated @RequestBody DeleteModel model) {
    constCohortService.delete(model.getId());
    return ResponseHelper.response(true);
  }
}
