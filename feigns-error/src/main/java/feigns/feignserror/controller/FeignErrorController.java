package feigns.feignserror.controller;

import feigns.feignserror.service.FeignErrorService;
import feigns.feignserror.util.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign-error")
public class FeignErrorController {

  @Autowired
  private FeignErrorService feignErrorService;

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public ResponseEntity add(@RequestParam String feignServiceName,
      @RequestParam String methodName) {

    return ResponseHelper
        .response(feignErrorService.incError(
            feignServiceName,
            methodName

        ));

  }

}
