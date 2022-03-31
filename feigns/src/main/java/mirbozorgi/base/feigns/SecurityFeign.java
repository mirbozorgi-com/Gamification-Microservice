package mirbozorgi.base.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient("security-service")
public interface SecurityFeign {

  @GetMapping("security/get-deviceid")
  Object getDeviceId(@RequestParam String uuid,
      @RequestHeader(name = "marketName") String marketName,
      @RequestHeader(name = "gamePackageName") String gamePackageName,
      @RequestHeader(name = "env") String env);


  @RequestMapping(method = RequestMethod.POST, path = "/security/authorize")
  Object authorize(
      @RequestHeader(required = false, name = "token") String token,
      @RequestHeader(required = false, name = "marketName") String marketName,
      @RequestHeader(required = false, name = "gamePackageName") String gamePackageName,
      @RequestHeader(required = false, name = "env") String env
  );

}
