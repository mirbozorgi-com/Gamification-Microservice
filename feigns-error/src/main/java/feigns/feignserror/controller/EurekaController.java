package feigns.feignserror.controller;

import feigns.feignserror.service.EurekaService;
import feigns.feignserror.util.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eureka")
public class EurekaController {

  @Autowired
  private EurekaService eurekaService;

  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getApplications() {

    return ResponseHelper
        .response(eurekaService.findAll());


  }

}
