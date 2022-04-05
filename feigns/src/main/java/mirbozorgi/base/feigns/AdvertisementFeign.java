package mirbozorgi.base.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient("advertisement-service")
public interface AdvertisementFeign {

  @RequestMapping(method = RequestMethod.GET, path = "package-third-party/get-by")
  Object getBy(
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader String marketName,
      @RequestParam String name);

}