package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.AddCohortModel;
import com.mirbozorgi.api.model.DeleteModel;
import com.mirbozorgi.api.model.UpdateCohortModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.CohortService;
import com.mirbozorgi.business.service.SerializerService;
import java.io.IOException;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GameProperties;
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
@RequestMapping("/cohort")
public class CohortController {

  @Autowired
  private CohortService cohortService;

  @Autowired
  private SerializerService serializerService;

  @Autowired
  private CurrentContextService currentContextService;

  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody AddCohortModel model) {

    return ResponseHelper.response(cohortService.save(
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        model.getName(),
        currentContextService.getCurrentMarket(),
        model.getChance(),
        model.isActive(),
        model.getConstCohort(),
        StringUtils.isEmpty(model.getConfig())
            ? null
            : serializerService.toObj(model.getConfig(), Object.class)
        ,
        model.getDefaultCohort(),
        model.getMinVersionClient(),
        model.getMaxVersionClient()
    ));
  }

  @GameProperties
  @RequestMapping(value = "/choose-random", method = RequestMethod.GET)
  public ResponseEntity chooseCohort(
      @RequestParam int clientVersion,
      @RequestParam(required = false) String cohort) {
    return ResponseHelper.response(cohortService.chooseRandom(
        clientVersion,
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        cohort
    ));
  }


  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(@RequestParam String name, @RequestParam String packageName,
      @RequestParam String market) {
    return ResponseHelper
        .response(cohortService.findByNameAndPackageNameAndMarket(name, packageName, market));
  }


  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll() {
    return ResponseHelper.response(cohortService.findAll());
  }

  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(@RequestBody UpdateCohortModel model) {
    return ResponseHelper.response(cohortService.update(
        model.getId(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        model.getName(),
        currentContextService.getCurrentMarket(),
        model.getChance(),
        model.isActive(),
        model.getConstCohort(),
        model.getConfig(),
        model.getDefaultCohort(),
        model.getMinVersionClient(),
        model.getMaxVersionClient()

    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity delete(@Validated @RequestBody DeleteModel model) {
    cohortService.delete(model.getId());
    return ResponseHelper.response(true);
  }

  @GameProperties
  @RequestMapping(value = "/get-config", method = RequestMethod.GET)
  public ResponseEntity getConfig(
      @RequestParam String cohortName
  )
      throws IOException {
    return ResponseHelper
        .response(cohortService
            .getConfigCohort(currentContextService.getCurrentGamePackageName(), cohortName,
                currentContextService.getCurrentMarket()));
  }


}
