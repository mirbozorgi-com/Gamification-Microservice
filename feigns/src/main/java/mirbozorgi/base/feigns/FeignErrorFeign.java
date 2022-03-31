package mirbozorgi.base.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient("feignError-service")
public interface FeignErrorFeign {

  @RequestMapping(method = RequestMethod.GET, path = "feign-error/add")
  Object add(@RequestParam String feignServiceName, @RequestParam String methodName);

  @RequestMapping(value = "feign-maintain/refresh", method = RequestMethod.GET)
  void refresh(@RequestParam String host, @RequestParam int port);

  @RequestMapping(value = "feign-maintain/restart", method = RequestMethod.GET)
  void restart(@RequestParam String host, @RequestParam int port);

  @RequestMapping(value = "feign-maintain/shutdown", method = RequestMethod.GET)
  void shutdown(@RequestParam String host, @RequestParam int port);
}
