package feigns.feignserror.controller;

import feigns.feignserror.service.MicroServiceRegistryService;
import feigns.feignserror.util.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/micro-service-registry")
public class MicroServiceRegistryController {

  @Autowired
  private MicroServiceRegistryService microServiceRegistryService;


  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(
      @RequestParam String name,
      @RequestParam int level) {

    return ResponseHelper
        .response(microServiceRegistryService.add(name, level));

  }


  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity find(
      @RequestParam String name) {
    return ResponseHelper
        .response(microServiceRegistryService.findByName(name));

  }


  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity findAll() {

    return ResponseHelper
        .response(microServiceRegistryService.findAll());

  }

}
