package com.mirbozorgi.eureka.second;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  @RequestMapping(value = "/health", method = RequestMethod.GET, produces = "text/plain")
  public String monitor() {

    int app_check = 1;

    StringBuilder builder = new StringBuilder();

    String result =
        String.format(
            "mirbozorgi_app %d \n",
            app_check);

    builder.append(result);

    return builder.toString();
  }

}
