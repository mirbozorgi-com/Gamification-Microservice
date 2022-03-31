package com.mirbozorgi.springcloud.apigateway.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("security-service")
public interface SecurityFeignService {

  @RequestMapping(method = RequestMethod.POST, path = "/security/authorize")
  Object authorize(@RequestHeader(required = false, name = "token") String token,
      @RequestHeader(name = "marketName") String marketName,
      @RequestHeader(name = "gamePackageName") String gamePackageName,
      @RequestHeader(name = "env") String env);
}
