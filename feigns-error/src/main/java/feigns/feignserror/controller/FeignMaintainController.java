package feigns.feignserror.controller;

import feigns.feignserror.service.FeignMaintainService;
import feigns.feignserror.util.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign-maintain")
public class FeignMaintainController {


  @Autowired
  private FeignMaintainService feignMaintainService;


  @RequestMapping(value = "/refresh", method = RequestMethod.GET)
  public ResponseEntity refresh(@RequestParam String host, @RequestParam int port) {
    feignMaintainService.refresh(host, port);
    return ResponseHelper.response(true);
  }

  @RequestMapping(value = "/shutdown", method = RequestMethod.GET)
  public ResponseEntity shutdown(@RequestParam String host, @RequestParam int port) {
    feignMaintainService.shutDown(host, port);
    return ResponseHelper.response(true);
  }

  @RequestMapping(value = "/restart", method = RequestMethod.GET)
  public ResponseEntity restart(@RequestParam String host, @RequestParam int port) {
    feignMaintainService.restart(host, port);
    return ResponseHelper.response(true);
  }


  @RequestMapping(value = "/check-availability", method = RequestMethod.GET)
  public ResponseEntity checkAvailability() {
    return ResponseHelper
        .response(feignMaintainService.checkAvailability());
  }


}
